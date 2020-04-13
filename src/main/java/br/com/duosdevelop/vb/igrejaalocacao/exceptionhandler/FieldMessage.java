package br.com.duosdevelop.vb.igrejaalocacao.exceptionhandler;

import java.io.Serializable;

public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 1L;

        private String messageUser;
        private String messageDev;

        public FieldMessage(String messageUser, String messageDev) {
            this.messageUser = messageUser;
            this.messageDev = messageDev;
        }

        public String getMessageUsuario() {
            return messageUser;
        }

        public String getMessageDesenvolvedor() {
            return messageDev;
        }
}
