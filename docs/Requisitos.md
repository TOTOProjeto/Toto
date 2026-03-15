# 📋 Requisitos do Sistema — Toto

## 1. Introdução

O Toto é um sistema de gerenciamento de projetos baseado na metodologia Kanban, desenvolvido em Java com Spring Boot.  
A plataforma tem como objetivo permitir a organização visual de tarefas, colaboração entre membros da equipe e acompanhamento do progresso dos projetos em tempo real.

---

# 2. Requisitos Funcionais (RF)

## 2.1 Gestão de Usuários

RF01 – O sistema deve permitir o cadastro de usuários.  
RF02 – O sistema deve permitir autenticação de usuários.  
RF03 – O sistema deve permitir que um usuário edite suas informações pessoais.  
RF04 – O sistema deve permitir que o administrador visualize todos os usuários cadastrados.  
RF05 – O administrador deve poder gerenciar usuários da plataforma.

---

## 2.2 Gestão de Projetos

RF06 – O sistema deve permitir que um usuário crie um projeto.  
RF07 – O sistema deve permitir definir nome e descrição do projeto.  
RF08 – O sistema deve permitir associar um ou mais quadros a um projeto.  
RF09 – O sistema deve permitir convidar membros para participar de um projeto.  
RF10 – O sistema deve permitir definir o papel do usuário dentro do projeto (Criador, Membro).  

---

## 2.3 Gestão de Quadros Kanban

RF11 – O sistema deve permitir a criação de quadros Kanban.  
RF12 – O sistema deve permitir a criação de colunas personalizáveis.  
RF13 – O sistema deve permitir editar e excluir colunas.  
RF14 – O sistema deve exibir as tarefas organizadas visualmente por colunas.  

---

## 2.4 Gestão de Tarefas (Cartões)

RF15 – O sistema deve permitir criar cartões dentro de uma coluna.  
RF16 – O cartão deve conter título, descrição, responsável e prazo.  
RF17 – O sistema deve permitir mover cartões entre colunas.  
RF18 – O sistema deve permitir editar informações do cartão.  
RF19 – O sistema deve permitir excluir cartões.  
RF20 – O sistema deve registrar o histórico de movimentações dos cartões.  

---

## 2.5 Colaboração

RF21 – O sistema deve permitir adicionar comentários aos cartões.  
RF22 – O sistema deve permitir mencionar outros usuários nos comentários.  
RF23 – Todas as interações devem ficar vinculadas ao cartão correspondente.  

---

## 2.6 Controle de Acesso

RF24 – O sistema deve restringir ações de acordo com o papel do usuário no projeto.  
RF25 – Apenas o criador do projeto pode gerenciar permissões dos membros.  
RF26 – O administrador deve ter acesso global para supervisão da plataforma.  

---

# 3. Requisitos Não Funcionais (RNF)

RNF01 – O sistema deve ser desenvolvido em Java 17 ou superior.  
RNF02 – O sistema deve utilizar Spring Boot para construção da API REST.  
RNF03 – O sistema deve implementar controle de autenticação e autorização.  
RNF04 – O sistema deve registrar histórico de alterações para garantir rastreabilidade.  
RNF05 – A interface deve apresentar organização visual no estilo Kanban.  
RNF06 – O sistema deve permitir múltiplos usuários simultâneos.  
RNF07 – O sistema deve garantir integridade e consistência dos dados.  

---

# 4. Regras de Negócio (RN)

RN01 – Um usuário pode participar de múltiplos projetos.  
RN02 – Um usuário pode ter diferentes níveis de permissão em projetos distintos.  
RN03 – Cada cartão deve estar obrigatoriamente vinculado a uma coluna.  
RN04 – Cada coluna deve pertencer a um único quadro.  
RN05 – Cada quadro deve estar associado a um projeto.  
RN06 – O histórico de movimentação de cartões não pode ser apagado.  

---

# 5. Objetivo do Sistema

O Toto tem como objetivo oferecer uma solução simples, visual e colaborativa para gerenciamento de projetos, promovendo:

- Organização clara do fluxo de trabalho  
- Colaboração eficiente entre equipes  
- Acompanhamento do progresso em tempo real  
- Aumento da produtividade  

---

Toto — Gestão de projetos simples, visual e colaborativa.
