ATENCAO: TEXTO EM CONSTRUÇÃO/REVISÃO.

# POC Apresentação
Projeto academico(Especialização em Arquitetura de Sistemas Distribuídos PUC).
Essa POC tem como principal objetivo demonstrar a integração entre dois microserviços a partir de um produto fictício chamado Easy Quality. 
O Easy Quality é composto por uma suíte de ferramentas e dados integrados que permitem às empresas fazerem gestão da qualidade de acordo com o momento e estratégia de negócio que vivem. Com módulos desenhados no formato escada de produtos, conseguimos incrementar ou remover  módulos de software levando assim aos clientes ótima experiência na sua jornada, pois a transformação digital impões time to market agressivo para empresas pequenas, médias ou grandes. Foi pensando assim que desenhamos uma arquitetura de software que possibilite: escalabilidade, extensibilidade, disponibilidade, performance, tolerância a falha e resiliência tudo isso iplementado em nuven utilizando k8s.

# Meu objetivo
Esse material tem a proposta de apesentar o que implementei envolvendo: Eventos, Micro Serviços, CQRS e algumas características da arquitetura Hexagonal. 
Não é o lançamento do produto EasyQuality. BUGs existem e melhorias de negócio certamente será necesário principalmente quando falamos em domínios negociais.

Ficaria muito feliz se a partir desse ponta pé inicial surgissem opniões que nos levassem ao aprendizado contínio pricipalmente nos aspéctos arquiteturais propostos.

# Documentos
- [TCC em PDF](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/TCC-Rian-Vasconcelos.pdf)

# Domínios
O Produto atualmente é comtemplado com o módulo(domínio) de conformidades mas que terá a proposta de lancar features em diversos módulos novos como por exemplo gestão de riscos, indicadores e outros. Sendo assim é de extrema importancia o entendimento e coerencia de cada domínio de negócio para que esse produto fictício seja evoluído sem invasão de contexto, tornando cada vez mais assertiva a proposta arquitetural escolhida. 


# Arquitetura 
EVENT DRIVEN E MICROSERVICE USANDO COMMAND QUERY RESPONSABILITY SEGREGATION 

## Ilustrações

- [Componentes](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/ilustracoes/picture1.png)
- [Interação de componentes com o barramento de eventos](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/ilustracoes/picture2.png)
- [Protótipo Arquitetural](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/ilustracoes/picture3.png)
- [CQRS](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/ilustracoes/picture4.png)
- [Implantação](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/ilustracoes/picture5.png)
- [Componentes K8s](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/ilustracoes/picture6.png)

## Pré requisitos
* Java 14
* Maven
* Lombok
* Docker (No diretório docker presente na raiz dessa POC execute o docker-compose.yml para disponibilizar o KAFKA)

# Spring Boot 
 - Micro serciço Questionario
 - Micro serciço Inspeçao

# Postman Collection 
* [Domínio conformidades](https://github.com/rianmachado/easy-quality/blob/master/dominio-conformidades/collections/conformidades.postman_collection.json) 
  
# Dokerizando
Localize o arquivo Dockerfile que encontra-se no módulo de start de cada micro serviço.

# Infraestrutura
Acesse o diretório "eks" localizado na raiz dessa POC. La encontra-se os comandos utilizados para rodar a aplicação sobre o gestor de container(Kuberntes) utilizando o EKS da Amazon.

# Melhorias
Ainda não foi abordado neste trabalho assuntos de extrema importância. Padronização e melhor prática a ser aplicada na transformação dos dados capturados pelos adaptadores Kafka(assinantes de tópicos) e a implementação de Dead Letter Queues(DLQ) para tratamento de erros.

