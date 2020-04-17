package br.com.duosdevelop.vb.igrejaalocacao.services.utils;

import br.com.duosdevelop.vb.igrejaalocacao.services.exceptions.DateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateUtil {
    @Autowired
    private MessageSource messageSource;
    public Date toDate(String date) throws Exception {
        if (!date.matches("\\d{2}/\\d{2}/\\d{4}"))
            throw new DateException(messageSource.getMessage("message.format.date", null, LocaleContextHolder.getLocale()),
                    new ParseException("Data com formato "+date, 0));

        if (!date.matches("[0-3]?\\d/[0-1]?\\d/[12]\\d{3}"))
            throw new DateException(messageSource.getMessage("message.value.date", null, LocaleContextHolder.getLocale()),
                    new Exception("Data com valores incorretos "+date));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(date);
    }
}
