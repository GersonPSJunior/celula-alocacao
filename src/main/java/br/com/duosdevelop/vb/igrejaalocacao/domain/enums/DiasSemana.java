package br.com.duosdevelop.vb.igrejaalocacao.domain.enums;

public enum DiasSemana {
    DOMINGO(1, "Domingo"),
    SEGUNDA(2, "Segunda"),
    TERCA(3, "Terça"),
    QUARTA(4, "Quarta"),
    QUINTA(5, "Quinta"),
    SEXTA(6, "Sexta"),
    SABADO(7, "Sabádo");

    private Integer id;
    private String msg;

    DiasSemana(Integer id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public static DiasSemana toEnum(Integer id){
        if (id == null) return null;
        for (DiasSemana value : DiasSemana.values()) {
            if (value.getId().equals(id)) return value;
        }
        return null;
    }
}
