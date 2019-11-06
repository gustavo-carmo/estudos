databaseChangeLog = {

    changeSet(author: "gustavo (generated)", id: "1490872883106-1") {
        createTable(tableName: "address") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "addressPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "address_details", type: "VARCHAR(255)")

            column(name: "city", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "district", type: "VARCHAR(255)")

            column(name: "mobile", type: "VARCHAR(255)")

            column(name: "number", type: "VARCHAR(255)")

            column(name: "phone", type: "VARCHAR(255)")

            column(name: "state", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "zip_code", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-2") {
        createTable(tableName: "contractor") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "contractorPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status_wkf", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "wkf_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-3") {
        createTable(tableName: "customer") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "customerPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "contractor_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "document_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "document_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "validated_by_workfinity", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-4") {
        createTable(tableName: "defect") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "defectPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "contractor_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status_wkf", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "wkf_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-5") {
        createTable(tableName: "model") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "modelPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "contractor_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status_wkf", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "wkf_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-6") {
        createTable(tableName: "role") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "rolePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "authority", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-7") {
        createTable(tableName: "service_offered") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "service_offeredPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "contractor_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status_wkf", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "wkf_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-8") {
        createTable(tableName: "service_order") {
            column("""orei             """, autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "service_orderPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "attempts_to_communication", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "closing_date", type: "datetime")

            column(name: "code", type: "VARCHAR(255)")

            column(name: "contractor_id", type: "BIGINT")

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "customer_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "defect_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "limit_date", type: "datetime")

            column(name: "model_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "notes", type: "VARCHAR(255)")

            column(name: "opening_date", type: "datetime")

            column(name: "serial_number", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "service_offered_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "wkf_id", type: "BIGINT")
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-9") {
        createTable(tableName: "tenant") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tenantPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "identifier", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "login_field", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "status", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)")

            column(name: "url_support_portal", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "url_wkf", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "wkf_password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "wkf_username", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-10") {
        createTable(tableName: "token_to_reset_password") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "token_to_reset_passwordPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "token", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-11") {
        createTable(tableName: "user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "userPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "account_expired", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "account_locked", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "created_date", type: "datetime") {
                constraints(nullable: "false")
            }

            column(name: "customer_id", type: "BIGINT")

            column(name: "email", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "enabled", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "last_login", type: "datetime")

            column(name: "name", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "password_expired", type: "BIT") {
                constraints(nullable: "false")
            }

            column(name: "tenant_id", type: "BIGINT")

            column(name: "username", type: "VARCHAR(50)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-12") {
        createTable(tableName: "user_role") {
            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "role_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-13") {
        addPrimaryKey(columnNames: "user_id, role_id", constraintName: "user_rolePK", tableName: "user_role")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-14") {
        addUniqueConstraint(columnNames: "authority", constraintName: "UC_ROLEAUTHORITY_COL", tableName: "role")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-15") {
        addForeignKeyConstraint(baseColumnNames: "contractor_id", baseTableName: "defect", constraintName: "FK14cn63rb648hyfpju03qvjp86", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contractor")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-16") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "defect", constraintName: "FK2rl2itptmj2vus8xsi7lj064n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-17") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "token_to_reset_password", constraintName: "FK31ig7uyn2kred7erhwvvfdaex", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-18") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "customer", constraintName: "FK4cssuhac3rgief0sog5u0bxtu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-19") {
        addForeignKeyConstraint(baseColumnNames: "contractor_id", baseTableName: "model", constraintName: "FK618iy1b2uhf3wwhwf8r1ulwh5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contractor")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-20") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "user_role", constraintName: "FK859n2jvi8ivhui0rl0esws6o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "user")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-21") {
        addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "user_role", constraintName: "FKa68196081fvovjhkek5m97n3y", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-22") {
        addForeignKeyConstraint(baseColumnNames: "customer_id", baseTableName: "user", constraintName: "FKdptx0i3ky01svofwjytq5iry0", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "customer")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-23") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "user", constraintName: "FKelinkvp4cx2ta4n5somgg77e2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-24") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "model", constraintName: "FKfp6fd8hksiilktebio7xxdc25", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-25") {
        addForeignKeyConstraint(baseColumnNames: "model_id", baseTableName: "service_order", constraintName: "FKgjk9i3noegsdvo65y6q2cgi2n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "model")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-26") {
        addForeignKeyConstraint(baseColumnNames: "address_id", baseTableName: "customer", constraintName: "FKglkhkmh2vyn790ijs6hiqqpi", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "address")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-27") {
        addForeignKeyConstraint(baseColumnNames: "contractor_id", baseTableName: "customer", constraintName: "FKgtt62ukjhonelkw3gbirguyiv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contractor")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-28") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "service_offered", constraintName: "FKi2h0uj04cbmf4ppggeppr7a2x", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-29") {
        addForeignKeyConstraint(baseColumnNames: "contractor_id", baseTableName: "service_offered", constraintName: "FKifjuw95u74nvxl6jrqtffapi4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contractor")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-30") {
        addForeignKeyConstraint(baseColumnNames: "customer_id", baseTableName: "service_order", constraintName: "FKk1rjh79i7vwbu9nb7pc88tjrv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "customer")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-31") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "service_order", constraintName: "FKn734h4kd2t6lk0ptjgffb3ypv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-32") {
        addForeignKeyConstraint(baseColumnNames: "defect_id", baseTableName: "service_order", constraintName: "FKqy7y4nekulmdukmevlhgbp36r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "defect")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-33") {
        addForeignKeyConstraint(baseColumnNames: "service_offered_id", baseTableName: "service_order", constraintName: "FKrg7h122rgbxxwlfva041cmque", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "service_offered")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-34") {
        addForeignKeyConstraint(baseColumnNames: "tenant_id", baseTableName: "contractor", constraintName: "FKrjsw5rn8vpflmaqo7s0dl6ys9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tenant")
    }

    changeSet(author: "gustavo (generated)", id: "1490872883106-35") {
        addForeignKeyConstraint(baseColumnNames: "contractor_id", baseTableName: "service_order", constraintName: "FKs0enu2ryf2phhfbrwps09x9og", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "contractor")
    }
}
