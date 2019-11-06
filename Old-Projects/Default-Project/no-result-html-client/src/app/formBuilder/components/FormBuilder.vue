<script>

  import ModuleTemplate from 'src/components/root/main/ModuleTemplate.vue'

  import draggable from 'vuedraggable'
  import SgsRender from 'src/components/utils/sgs/SgsRender.vue';
  import BasicComponentsBuild from './BasicComponentsBuild.vue';
  import FieldForm from './fieldForm/FieldForm.vue';

  import {mapActions} from 'vuex';
  import utils from '../FormBuilderUtils'

  export default{
    data() {
      return {
          instance: {},
          layout: {
              rows: [],
              fields: []
          },
          basicComponents: [
              'header',
              'text',
              'numeric',
              'date',
              'dateTime',
              'time',
              'select',
              // 'multiSelect',
              'textArea',
              // 'fileUpload',
              // 'password',
              // 'button',
              'boolean'
          ],
          customComponents: [
              'cnpj',
              'cpf',
              'currency',
              'email'
          ],
          dialogVisible: false,
          dialogFormData: {},
          formLabelWidth: '120px',
          debug: false,

          rules: {
          }

          /*
          name1: [
              { required: true, message: 'Por favor, preencha o campo', trigger: 'blur' }
            ]*/
      }
  },
  components: {
    draggable, SgsRender, BasicComponentsBuild, FieldForm, ModuleTemplate
  },
  created() {
  },
  mounted () {

    let self = this

    self.showLoader();

    self.formGetInstance(self.$route.params.id)
      .then(data => {
        self.hideLoader();
        self.instance = data;
        self.layout = self.instance.fieldDesc ? self.instance.fieldDesc : {rows: [], fields: []};
      })
  },
  methods: {
    ...mapActions([
      'showLoader',
      'hideLoader',
      'formGetInstance'
    ]),
    formBuilderSave: function() {
      let self = this;
      self.showLoader();
      utils.saveFieldDesc({
        id: this.instance.id,
        fieldDesc: this.layout
      }).then(response => {

          self.hideLoader();

          self.$notify({
            title: 'Sucesso',
            message: 'Form atualizado com sucesso.',
            type: 'success'
          });

        },
        response => {

          self.hideLoader();

          self.errors = response;
        });
    },
    fieldEdit: function(field) {
      this.dialogFormData = utils.fieldBuildObject(field, this.layout.fields);
      this.dialogShow();
    },
    dialogShow: function() {
      this.dialogVisible = true;
    },
    dialogClose: function() {
      this.dialogVisible = false;
    },
    dialogSave: function() {
      this.$refs["dialogFormData"].validate((valid) => {
        if (valid) {
          var currentField = utils.findField(this.dialogFormData.id, this.layout.fields);
          if (currentField == undefined) {
            this.layout.fields.push(this.dialogFormData);
          } else {
            Object.assign(currentField, this.dialogFormData);
          }
          this.dialogClose();
        } else {
          console.log('error submit!!');
        }
      });
    },
    fieldRemove: function(fields, fieldPosition) {

      this.$confirm('Deseja realmente remover o componente?', 'Aviso!!!', {
        confirmButtonText: 'Sim',
        cancelButtonText: 'Não',
        type: 'error'
      }).then(() => {

        var elRemove = fields[fieldPosition];
        fields.splice(fieldPosition, 1);

        var indexRemove = utils.findFieldIndex(elRemove.id, this.layout.fields);
        if (indexRemove != undefined) {
          this.layout.fields.splice(indexRemove, 1);
        }

        this.$message({
          type: 'success',
          message: 'Componente removido.'
        });
      });
    },
    getField: function(id) {
      return utils.findField(id, this.layout.fields);
    },
    clone: function(original) {
      return {
        id: utils.generateId(),
        type: original
      };
    },
    addRow: function(quantity) {
      utils.addRow(this.layout.rows, quantity);
    },
    resolveTitle: function() {
      return this.instance.name + " - Form Builder";
    },
    backToForm: function() {
      let self = this;
      self.$router.push('/form/show/' + self.instance.id);
    }
  }
}

</script>

<template>

  <module-template fluid="true">
    <modulePanel>

      <div class="row" slot="header">
        <div class="col-xs-4">
          <h3>{{resolveTitle()}}</h3>
        </div>
        <div class="col-xs-8">
          <div class="pull-right">
            <el-button type="primary" @click="formBuilderSave">
              <i class="glyphicon glyphicon-ok" />
              Salvar
            </el-button>
            <el-button type="" @click="backToForm">
              <i class="glyphicon glyphicon-chevron-left" aria-hidden="true" />
              Voltar para Form
            </el-button>
          </div>
        </div>
      </div>

      <div class="row" slot="body">
        <div class="col-xs-2">

          <el-collapse>
            <el-collapse-item title="Linhas" name="1">
              <div>
                <div class="row">
                  <div class="col-xs-6">
                    <el-button @click="addRow(1)" size="small">
                      1 Coluna
                    </el-button>
                  </div>
                  <div class="col-xs-6">
                    <el-button @click="addRow(2)" size="small">
                      2 Colunas
                    </el-button>
                  </div>
                </div>
              </div>
              <div>
                <div class="row">
                  <div class="col-xs-6">
                    <el-button @click="addRow(3)" size="small">
                      3 Colunas
                    </el-button>
                  </div>
                  <div class="col-xs-6">
                    <el-button @click="addRow(4)" size="small">
                      4 Colunas
                    </el-button>
                  </div>
                </div>
              </div>
            </el-collapse-item>
            <el-collapse-item title="Comp. básicos" name="2">
                <draggable
                  v-model="basicComponents"
                  class="dragArea"
                  :options="{group:{ name: 'fields', pull: 'clone', put: true }, sort: false}"
                  :clone="clone"
                >
                  <template v-for="(type, index) in basicComponents">
                    <BasicComponentsBuild :type="type"></BasicComponentsBuild>
                  </template>
                </draggable>
            </el-collapse-item>
            <el-collapse-item title="Comp. customizados" name="3">
                <draggable
                  v-model="customComponents"
                  class="dragArea"
                  :options="{group:{ name: 'fields', pull: 'clone', put: true }, sort: false}"
                  :clone="clone"
                >
                  <template v-for="(type, index) in customComponents">
                    <BasicComponentsBuild :type="type"></BasicComponentsBuild>
                  </template>
                </draggable>
            </el-collapse-item>
          </el-collapse>

        </div>

        <div class="col-xs-10" id="grid-work">

          <el-form label-position="top" label-width="100px">

            <draggable v-model="layout.rows" :options="{group:'rows'}" class="dragArea">

              <div class="row" v-for="(row, index) in layout.rows">

                <div v-for="(col, index) in row.cols"
                     :class="{
                      'col-xs-3': row.cols.length == 4,
                      'col-xs-4': row.cols.length == 3,
                      'col-xs-6': row.cols.length == 2,
                      'col-xs-12': row.cols.length == 1
                    }"
                >
                   <draggable v-model="col.fields" :options="{group: 'fields'}" class="dragArea">
                      <template v-for="(field, index) in col.fields">
                        <div>
                          <div class="field-actions">
                              <el-button type="text" icon="setting" size="mini" :plain="true" @click="fieldEdit(field)"></el-button>
                              <el-button type="text" icon="delete" size="mini" :plain="true" @click="fieldRemove(col.fields, index)"></el-button>
                          </div>
                          <SgsRender :type="field.type" :attr="getField(field.id)"/>
                        </div>
                      </template>
                  </draggable>

                </div>

              </div>
            </draggable>

          </el-form>

          <template v-if="debug">
            <hr/>
            Layout: <br/>
            {{ layout }}
            <hr/>
            Fields: <br/>
            {{ layout.fields }}
            <hr/>
            Rows: <br/>
            {{ layout.rows }}
            <hr/>
            Basic Components: <br/>
            {{ basicComponents }}

            <hr/>
            Custom Components: <br/>
            {{ customComponents }}
          </template>

        </div>

        <el-dialog title="Editar componente" :visible.sync="dialogVisible" size="large">
            <el-form label-position="top" :rules="rules" :model="dialogFormData" ref="dialogFormData">
              <div class="row">
                <div class="col-xs-8 border-right">
                  <FieldForm :type="dialogFormData.type" :fieldData="dialogFormData" :rules="rules"></FieldForm>
                </div>
                <div class="col-xs-4">
                  <h4>Preview</h4>
                  <SgsRender :type="dialogFormData.type" :attr="dialogFormData"/>
                </div>
              </div>
            </el-form>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogClose()">Cancel</el-button>
              <el-button type="primary" @click="dialogSave()">Salvar</el-button>
            </span>
        </el-dialog>

      </div>

    </modulePanel>
  </module-template>
</template>

<style scoped>
.normal {
  background-color: grey;
}

.drag {
  background-color: green;
}

.dragArea {
  min-height: 35px;
}

/*style="border: 1px solid red; padding-left: 20px"*/
#grid-work {
    /*border: 1px dotted #ccc;*/
}

#grid-work div.row {
    border-left: 5px solid #ccc;
    margin-bottom: 5px;
}

#grid-work div.row div.col {
}

#grid-work div.col-xs-12, #grid-work div.col-xs-6, #grid-work div.col-xs-4, #grid-work div.col-xs-3 {
    border: 1px dotted #ccc;
    min-height: 35px;
}

.field-actions {
    font-size: 12px;
    color: #999;
    float: right;
}

.border-right {
  border-right: 1px solid #f1f1f1;
}
</style>

