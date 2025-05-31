package com.feedback.feedback.models;

import java.util.Arrays;
import java.util.Optional;

public enum TopicsEnum {
    PROATIVO_ENGAJADO(1, "Foi proativo e engajado", 1),
    COMUNICOU_BEM(2, "Se comunicou bem com a equipe", 1),
    CONTRIBUIU_IDEIAS(3, "Contribuiu com boas ideias", 1),
    AJUDOU_COLEGAS(4, "Ajudou colegas quando necessário", 1),
    CUMPRIU_PRAZOS(5, "Cumpriu prazos e entregas", 1),
    INICIATIVA_LIDERANCA(6, "Mostrou iniciativa ou liderança", 1),
    POUCO_INTERESSE(7, "Demonstrou pouco interesse", -1),
    COMUNICACAO_FALHOU(8, "Comunicação falhou ou foi limitada", -1),
    ATRASOS(9, "Teve atrasos ou faltas injustificadas", -1),
    FALTOU_ORGANIZACAO(10, "Faltou organização ou foco", -1),
    COMENTARIOS_INADEQUADOS(11, "Fez comentários ou piadas inadequadas", -1),
    OUTRO(12, "Outro", 0);

    private final int id;
    private final String topic;
    private final int points;

    TopicsEnum(int id, String topic, int points) {
        this.id = id;
        this.topic = topic;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public int getPoints() {
        return points;
    }

    public static Optional<TopicsEnum> fromId(int id) {
        return Arrays.stream(TopicsEnum.values())
                .filter(t -> t.id == id)
                .findFirst();
    }

    public boolean isLike() {
        return points > 0;
    }
}
