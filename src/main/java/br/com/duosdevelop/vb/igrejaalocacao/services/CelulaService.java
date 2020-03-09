package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.*;
import br.com.duosdevelop.vb.igrejaalocacao.domain.enums.DiasSemana;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewCelulaDTO;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.CelulaRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.DiscipuladoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.EnderecoRepository;
import br.com.duosdevelop.vb.igrejaalocacao.repositories.MembroRepository;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.DateException;
import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CelulaService {

    @Autowired
    private CelulaRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private DiscipuladoRepository discipuladoRepository;

    @Autowired
    private MessageSource messageSource;

    public List<Celula> findAll() {
        return repository.findAll();
    }

    public Celula find(Integer id) {
        Optional<Celula> celula = repository.findById(id);
        return celula.orElseThrow(() -> new ObjectNotFoundException("Célula não encontrada"));
    }

    @Transactional
    public Celula insert(Celula celula){
        celula.setId(null);
        Celula celulaResult = repository.save(celula);
        if (celulaResult.getMembros() != null) {
            celulaResult.setMembros(celulaResult.getMembros().stream().map(membro -> {
                Integer id = membro.getId();
                membro = membroRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id: " + id + ", Tipo:" + Membro.class.getName()));
                membro.setCelula(celulaResult);
                return membro;
            }).collect(Collectors.toList()));
            membroRepository.saveAll(celulaResult.getMembros());
        }
        enderecoRepository.save(celula.getEndereco());
        discipuladoRepository.save(celula.getDiscipulado());
        return celulaResult;
    }

    public Celula fromDTO(NewCelulaDTO newCelulaDTO) throws Exception {

        if (!newCelulaDTO.getHorario().matches("\\d{2}:\\d{2}"))
            throw new DateException(messageSource.getMessage("message.format.hour", null, LocaleContextHolder.getLocale()),
                    new ParseException("Horário com formato incorreto "+ newCelulaDTO.getHorario(), 0));
        Celula celula = new Celula(newCelulaDTO.getNome(), newCelulaDTO.getLider(), DiasSemana.toEnum(newCelulaDTO.getDia()));
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        celula.setHorario(sdf.parse(newCelulaDTO.getHorario()));
        celula.setDiscipulado(new Discipulado(newCelulaDTO.getDiscipulado(), null, null, null));
        Cidade cidade = new Cidade(newCelulaDTO.getEndereco().getCidade(), null, new Estado(newCelulaDTO.getEndereco().getEstado(), null));
        Endereco endereco = new Endereco(newCelulaDTO.getEndereco().getRua(), newCelulaDTO.getEndereco().getNumero(), newCelulaDTO.getEndereco().getCep(), cidade);
        if (newCelulaDTO.getEndereco().getBairro() != null)
            endereco.setBairro(newCelulaDTO.getEndereco().getBairro());
        if (newCelulaDTO.getEndereco().getComplemento() != null)
            endereco.setComplemento(newCelulaDTO.getEndereco().getComplemento());
        celula.setEndereco(endereco);
        if (newCelulaDTO.getMembros() != null)
            celula.setMembros(newCelulaDTO.getMembros().stream().map(Membro::new).collect(Collectors.toList()));
        return celula;
    }
}
