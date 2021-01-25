package mensagens;

public class Pokemon{
    private int nivel;

    private String nome;
    private String afinidade;

    public Pokemon(int nivel, String nome, String afinidade) {
        this.nivel = nivel;
        this.nome = nome;
        this.afinidade = afinidade;
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
                "afinidade" + afinidade +
                "nivel " + nivel;
    }
}
