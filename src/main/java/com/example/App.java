package com.example;

import com.example.db.DatabaseConnection;
import com.example.model.Estudante;
import java.sql.*;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Estudantes ===");
            System.out.println("1. Criar Estudante");
            System.out.println("2. Listar Estudantes");
            System.out.println("3. Atualizar Estudante");
            System.out.println("4. Deletar Estudante");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: criarEstudante(); break;
                case 2: listarEstudantes(); break;
                case 3: atualizarEstudante(); break;
                case 4: deletarEstudante(); break;
                case 5: System.out.println("Saindo..."); scanner.close(); return;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarEstudante() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Curso: ");
        String curso = scanner.nextLine();

        String sql = "INSERT INTO estudantes (nome, idade, curso) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, idade);
            pstmt.setString(3, curso);
            pstmt.executeUpdate();
            System.out.println("Estudante criado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar estudante: " + e.getMessage());
        }
    }

    private static void listarEstudantes() {
        String sql = "SELECT * FROM estudantes";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudante estudante = new Estudante(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getInt("idade"),
                    rs.getString("curso")
                );
                System.out.println(estudante);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar estudantes: " + e.getMessage());
        }
    }

    private static void atualizarEstudante() {
        System.out.print("ID do estudante a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo curso: ");
        String curso = scanner.nextLine();

        String sql = "UPDATE estudantes SET nome = ?, idade = ?, curso = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, idade);
            pstmt.setString(3, curso);
            pstmt.setInt(4, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estudante atualizado com sucesso!");
            } else {
                System.out.println("Estudante não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar estudante: " + e.getMessage());
        }
    }

    private static void deletarEstudante() {
        System.out.print("ID do estudante a deletar: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM estudantes WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Estudante deletado com sucesso!");
            } else {
                System.out.println("Estudante não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar estudante: " + e.getMessage());
        }
    }
}