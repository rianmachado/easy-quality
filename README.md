ATENCAO: TEXTO EM CONSTRUÇÃO/REVISÃO.

# POC Apresentação
Projeto academico(Especialização em Arquitetura de Sistemas Distribuídos PUC).
Essa POC tem como pricipal objetivo demonstrar a integração entre dois microserviços a partir de um produdo fictício chamado Easy Quality. 
O Easy Quality é composto por uma suíte de ferramentas e dados integrados quem permitem às empresas fazerem gestão da qualidade de acordo com o momento e estratégia de negócio que vivem. Com módulos desenhados no formato escada de produtos, conseguimos incrementar ou remover  módulos de software levando assim aos clientes ótima experiência na sua jornada, pois a transformação digital impões time to market agressivo para empresas pequenas, médias ou grandes. Foi pensando assim que desenhamos uma arquitetura de software que possibilite: escalabilidade, extensibilidade, disponibilidade, performance, tolerância a falha e resiliência tudo isso iplementado em nuven sobre tecnologias de orquestração de containers.

# Documento completo formato acadêmico 
 [<<>>]TCC PDF 
 [<<>>]TCC PPT

# Domínios
O Produto atualmente é comtemplado com o módulo(domínio) de conformidades mas que terá a proposta de lancar features em diversos módulos novos como por exemplo gestão de riscos, indicadores e outros. Sendo assim é de extrema importancia o entendimento e coerencia de cada domínio de negócio para que esse produto fictício seja evoluído sem invasão de contexto, tornando cada vez mais assertiva a proposta arquitetural escolhida. 


# Arquitetura 
EVENT DRIVEN E MICROSERVICE USANDO COMMAND QUERY RESPONSABILITY SEGREGATION 

## Pré requisitos
* Java 14
* Maven
* Lombok
* Docker (No diretório docker presente na raiz dessa POC execute o docker-compose.yml para disponibilizar o KAFKA)

# Spring Boot 
 - Micro serciço Questionario
 - Micro serciço Inspeçao
 
# Dokerizando
Localize o arquivo Dockerfile localizado na raiz de cada micro serviço.

# Infraestrutura
Acesse o diretório "eks" localizado na raiz dessa POC. La encontra-se os comandos utilizados para rodar a aplicação sobre o gestor de container(Kuberntes) utilizando o EKS da Amazon.

