package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();
    private double xp = 0.0;

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
        System.out.println(nome + " inscrito no bootcamp: " + bootcamp.getNome());
    }

    public void progredir() {
        if (!conteudosInscritos.isEmpty()) {
            Conteudo proximoConteudo = conteudosInscritos.iterator().next();
            conteudosConcluidos.add(proximoConteudo);
            conteudosInscritos.remove(proximoConteudo);

            double xpGanho = proximoConteudo.calcularXp();
            xp += xpGanho;

            System.out.println(nome + " progrediu no conteúdo: " + proximoConteudo.getTitulo());
            System.out.println("XP ganho: " + xpGanho);
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");

        sb.append("Conteúdos Inscritos:\n");
        if (!conteudosInscritos.isEmpty()) {
            for (Conteudo conteudo : conteudosInscritos) {
                sb.append("\t").append(conteudo).append("\n");
            }
        } else {
            sb.append("\t(nenhum conteúdo inscrito)\n");
        }

        sb.append("Conteúdos Concluídos:\n");
        if (!conteudosConcluidos.isEmpty()) {
            for (Conteudo conteudo : conteudosConcluidos) {
                sb.append("\t").append(conteudo).append("\n");
            }
        } else {
            sb.append("\t(nenhum conteúdo concluído)\n");
        }

        sb.append("XP: ").append(xp).append("\n");

        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
