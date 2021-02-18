package mensagens;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import serializacao.CborSerializable;

public class Pokemon implements CborSerializable {
    @JsonProperty("nivel")
    private int nivel;
    @JsonProperty("evolucao")
    private Pokemon evolucao;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("afinidade")
    private String afinidade;

    @JsonCreator
    public Pokemon( int nivel,String nome,String afinidade,Pokemon evolucao) {
        this.nivel = nivel;
        this.nome = nome;
        this.afinidade = afinidade;
        this.evolucao = evolucao;
    }

    public Pokemon getEvolucao() {
        return evolucao;
    }

    public void setEvolucao(Pokemon evolucao) {
        this.evolucao = evolucao;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAfinidade() {
        return afinidade;
    }

    public void setAfinidade(String afinidade) {
        this.afinidade = afinidade;
    }

    @Override
    public String toString(){
        return "nome: "+ nome +
                " afinidade " + afinidade +
                " nivel " + nivel;
    }
}
