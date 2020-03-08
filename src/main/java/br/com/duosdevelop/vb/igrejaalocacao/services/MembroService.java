package br.com.duosdevelop.vb.igrejaalocacao.services;

import br.com.duosdevelop.vb.igrejaalocacao.domain.Cidade;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Endereco;
import br.com.duosdevelop.vb.igrejaalocacao.domain.Membro;
import br.com.duosdevelop.vb.igrejaalocacao.dto.NewMembroDTO;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MembroService {

    @Autowired
    private MembroRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MessageSource messageSource;

    public List<Membro> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Membro insert(Membro membro) {
        membro.setId(null);
        membro = repository.save(membro);
        enderecoRepository.saveAll(membro.getEnderecos());
        return  membro;
    }

    public Membro find(Integer id) {
        Optional<Membro> membro = repository.findById(id);
        return membro.orElseThrow(() -> new ObjectNotFoundException("Membro não encontrado"));
    }

    public Membro fromDTO(NewMembroDTO newMembroDTO) throws Exception {

        if (!newMembroDTO.getNascimento().matches("\\d{2}/\\d{2}/\\d{4}"))
            throw new DateException(messageSource.getMessage("message.format.date", null, LocaleContextHolder.getLocale()),
                    new ParseException("Data com formato "+newMembroDTO.getNascimento(), 0));

        if (!newMembroDTO.getNascimento().matches("[0-3]?\\d/[0-1]?\\d/[12]\\d{3}"))
            throw new DateException(messageSource.getMessage("message.value.date", null, LocaleContextHolder.getLocale()),
                    new Exception("Data com valores incorretos "+newMembroDTO.getNascimento()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Membro membro = new Membro(newMembroDTO.getNome(), sdf.parse(newMembroDTO.getNascimento()), newMembroDTO.getCpf());
        Cidade cidade = new Cidade(newMembroDTO.getCidade(), null, null);
        Endereco endereco = new Endereco(newMembroDTO.getRua(), newMembroDTO.getNumero(), newMembroDTO.getCep(), cidade);
        endereco.setMembro(membro);
        if (newMembroDTO.getBairro() != null)
            endereco.setBairro(newMembroDTO.getBairro());
        if (newMembroDTO.getComplemento() != null)
            endereco.setComplemento(newMembroDTO.getComplemento());
        membro.setEnderecos(Arrays.asList(endereco));
        membro.getTelefone().add(newMembroDTO.getTelefone1());
        if (newMembroDTO.getTelefone2() != null)
            membro.getTelefone().add(newMembroDTO.getTelefone2());
        if (newMembroDTO.getTelefone3() != null)
            membro.getTelefone().add(newMembroDTO.getTelefone3());
        return membro;
    }
}
