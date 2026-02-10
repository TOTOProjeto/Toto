## 📋 Toto

Organize projetos, colabore com sua equipe e acompanhe tarefas de forma visual e eficiente.

## 🧭 Descrição

O **Toto** é um sistema de gerenciamento de projetos no estilo **Kanban**, desenvolvido em **Java com Spring Boot**.  
A plataforma permite que usuários criem **quadros de projetos**, organizem tarefas em colunas personalizáveis (como *A Fazer*, *Em Progresso* e *Concluído*) e convidem membros da equipe para colaborar na gestão das atividades.

## 👥 Perfis de Usuário

O sistema define três perfis principais:

- **Criador do Projeto**  
  Cria quadros Kanban, define colunas e gerencia permissões dos membros.

- **Membro da Equipe**  
  Colabora nos quadros, cria e movimenta cartões, adiciona comentários e acompanha o progresso das tarefas.

- **Administrador**  
  Supervisiona usuários, projetos, quadros e atividades da plataforma.

💡 Um mesmo usuário pode participar de vários projetos, com diferentes níveis de permissão em cada um.

## ⚙️ Lógica de Negócio

### 🔸 Criação de Projeto e Quadro

O usuário pode criar um projeto contendo um quadro Kanban com:

- Nome do projeto  
- Descrição  
- Colunas iniciais (ex: *Backlog*, *Em andamento*, *Concluído*)

Cada projeto pode possuir um ou mais quadros, gerenciados de forma independente.

### 🔸 Gestão de Tarefas (Cartões)

Dentro de um quadro, os usuários podem:

- Criar cartões com título, descrição, responsável e prazo  
- Mover cartões entre colunas  
- Atualizar informações da tarefa  

As movimentações de cartões são registradas para manter o histórico do projeto.

### 🔸 Colaboração e Comentários

Os membros da equipe podem:

- Adicionar comentários nos cartões  
- Mencionar outros usuários  
- Acompanhar alterações realizadas no projeto  

Todas as interações ficam vinculadas ao cartão correspondente.

### 🔸 Controle de Acesso

As ações disponíveis dependem do papel do usuário dentro do projeto, garantindo segurança e organização na colaboração entre os membros.

## 🧱 Tecnologias Utilizadas

- Java 17+  
- Spring Boot  
  - API REST  
  - Gerenciamento de dependências  
  - Segurança e controle de acesso  

## 🎯 Objetivo

Oferecer uma solução simples e colaborativa para gestão de projetos, com foco em:

- 📊 Organização visual do trabalho  
- 🤝 Colaboração eficiente entre equipes  
- ⏱️ Acompanhamento do progresso em tempo real  
- 🚀 Aumento da produtividade  

## ✨ Toto

**Gestão de projetos simples, visual e colaborativa.**
