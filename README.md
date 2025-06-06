# 💰 MoneyView-v2

## ✅ Status do Projeto
🚀 Em evolução — Versão refatorada

## 📌 Sobre o Projeto

O **MoneyView-v2** é uma versão aprimorada do sistema de gerenciamento financeiro pessoal **MoneyView**, com foco em **boas práticas de desenvolvimento de software**. Esta nova versão aplica os **princípios SOLID** e elimina **code smells**, visando um código mais limpo, modular, reutilizável e fácil de manter.

## 🛠️ Tecnologias Utilizadas
- **Java (JDK 17)**  
- **Swing** (interface gráfica)  
- **MySQL** (banco de dados relacional)  
- **JDBC** (conexão com o banco)  
- **Maven** (gerenciador de dependências e build)  
- **Git** (versionamento de código)

## 🧠 Boas Práticas Aplicadas
- Aplicação dos **princípios SOLID**:
  - **S**: Responsabilidade única (Single Responsibility Principle)
  - **O**: Aberto/Fechado (Open/Closed Principle)
  - **L**: Substituição de Liskov (Liskov Substitution Principle)
  - **I**: Segregação de interfaces (Interface Segregation Principle)
  - **D**: Inversão de dependência (Dependency Inversion Principle)
- Eliminação de **code smells** (métodos longos, alta coesão, acoplamento excessivo, etc.)
- Refatoração de classes DAO, lógica de negócios e interface gráfica para melhorar organização e manutenibilidade

## 👨‍💻 Desenvolvedor
- [Seu Nome Aqui]

## 🎯 Funcionalidades do Sistema
- Tela de login e cadastro de usuários  
- Sessão de usuário ativa e personalizada  
- Cadastro, edição e exclusão de **transações financeiras**  
- Visualização de **ganhos** e **despesas**  
- **Categorização de transações**  
- Interface gráfica intuitiva com feedback visual  
- Armazenamento em **MySQL** com acesso via **JDBC**

## ⚙️ Como Executar
1. Instale o **MySQL** e configure a base de dados com as tabelas necessárias.  
2. Atualize o arquivo `ConexaoMySQL.java` com os dados corretos da sua conexão (usuário, senha, URL).  
3. Importe o projeto como **Maven Project** na sua IDE.  
4. Compile e execute a partir da classe principal.

## 📁 Organização do Projeto
- `gui/` — Telas do sistema (Swing)  
- `data/` — Classes de dados (Entidades e DAOs)  
- `service/` — Lógica de negócio  
- `util/` — Utilitários e helpers

## 📌 Considerações
Este projeto foi desenvolvido como parte de uma atividade de refatoração com foco em qualidade de código, seguindo recomendações do curso. Embora sua estrutura base seja similar à versão anterior, o foco principal aqui está na **qualidade do design de software**.
