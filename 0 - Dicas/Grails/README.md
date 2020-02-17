#### Alteração manual no banco de dados (Mysql) ####

## EXEMPLO ##
_____________________________________________________________
import grails.util.Holders
import groovy.sql.Sql

def dataSource = Holders.grailsApplication.mainContext.getBean("dataSource")

Sql sql = new Sql(dataSource)

sql.executeInsert("insert ignore into city( name, state_id) values ('São Vicente do Seridó', (select id from state where name = 'Paraiba' and country_id = (select id from country where name = 'Brasil')));")
_____________________________________________________________

#### Alteração manual no banco de dados (Mysql) ####