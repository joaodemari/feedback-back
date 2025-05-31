package com.feedback.feedback.models;

import java.util.Arrays;
import java.util.Optional;

public enum TopicsEnum {
    PROATIVO_ENGAJADO(1, "Foi proativo e engajado"),
    COMUNICOU_BEM(2, "Se comunicou bem com a equipe"),
    CONTRIBUIU_IDEIAS(3, "Contribuiu com boas ideias"),
    AJUDOU_COLEGAS(4, "Ajudou colegas quando necessário"),
    CUMPRIU_PRAZOS(5, "Cumpriu prazos e entregas"),
    INICIATIVA_LIDERANCA(6, "Mostrou iniciativa ou liderança"),
    POUCO_INTERESSE(7, "Demonstrou pouco interesse"),
    COMUNICACAO_FALHOU(8, "Comunicação falhou ou foi limitada"),
    ATRASOS(9, "Teve atrasos ou faltas injustificadas"),
    FALTOU_ORGANIZACAO(10, "Faltou organização ou foco"),
    COMENTARIOS_INADEQUADOS(11, "Fez comentários ou piadas inadequadas"),
    OUTRO(12, "Outro");

    private final int id;
    private final String topic;

    TopicsEnum(int id, String topic) {
        this.id = id;
        this.topic = topic;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public static Optional<TopicsEnum> fromId(int id) {
        return Arrays.stream(TopicsEnum.values())
                .filter(t -> t.id == id)
                .findFirst();
    }
}
