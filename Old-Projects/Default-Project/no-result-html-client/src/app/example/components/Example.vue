<script>

  import SgsRender from 'src/components/utils/sgs/SgsRender.vue';

  export default {
      data() {
          return {
            header: {
                type: 'header',
                name: '',
                labelPt: 'Título da página 1',
                labelEs: '',
                labelEn: ''
            },
            text: {
                type: 'text',
                name: '', //Não está sendo utilizado
                labelPt: 'Usuário X',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: 'Usuário X',
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                prefix: '',
                sufix: '',
                mask: '', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                minSize: null,
                maxSize: null,
                regex: '' //Não está sendo utilizado
            },
            numeric: {
                type: 'numeric',
                name: '', //Não está sendo utilizado
                labelPt: 'Número N',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                prefix: '', //Não está sendo utilizado
                sufix: '', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                minValue: null,
                maxValue: null
            },
            date: {
                type: 'date',
                name: '', //Não está sendo utilizado
                labelPt: 'Data',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: 'Data',
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                regex: '' //Não está sendo utilizado
            },
            dateTime: {
              type: 'dateTime',
              name: '', //Não está sendo utilizado
              labelPt: 'Data e Tempo',
              labelEs: '', //Não está sendo utilizado
              labelEn: '', //Não está sendo utilizado
              placeholderPt: 'Data e Tempo',
              placeholderEs: '', //Não está sendo utilizado
              placeholderEn: '', //Não está sendo utilizado
              columnName: '', //Não está sendo utilizado
              required: '' //Não está sendo utilizado
            },
            time: {
                type: 'time',
                name: '', //Não está sendo utilizado
                labelPt: 'Tempo',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: 'Tempo',
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '' //Não está sendo utilizado
            },
            textArea: {
                type: 'textArea',
                name: '', //Não está sendo utilizado
                labelPt: 'Área de Texto',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: 'Área de Texto',
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                minSize: null,
                maxSize: null
            },
             password: {
                type: 'password',
                name: '', //Não está sendo utilizado
                labelPt: 'Senha',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: 'Senha', //Não está sendo utilizado
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                minSize: null,
                maxSize: null,
                regex: '' //Não está sendo utilizado
            },
             cpf: {
                type: 'cpf',
                name: '', //Não está sendo utilizado
                labelPt: 'CPF',
                placeholderPt: '999.999.999-99',
                mask: '999.999.999-99',
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
            },
            cnpj: {
                type: 'cnpj',
                name: '', //Não está sendo utilizado
                labelPt: 'CNPJ',
                placeholderPt: '99.999.999/9999-99',
                mask: '99.999.999/9999-99',
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
            },
            email: {
                type: 'email',
                name: '', //Não está sendo utilizado
                labelPt: 'E-mail',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: 'email@email.com',
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                prefix: '@',
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                regex: '' //Não está sendo utilizado
            },
            currency: {
                type: 'currency',
                name: '', //Não está sendo utilizado
                labelPt: 'Valor',
                labelEs: '', //Não está sendo utilizado
                labelEn: '', //Não está sendo utilizado
                placeholderPt: '9.999,99',
                placeholderEs: '', //Não está sendo utilizado
                placeholderEn: '', //Não está sendo utilizado
                prefix: '$',
                mask: '#.###,##', //Não está sendo utilizado
                columnName: '', //Não está sendo utilizado
                required: '', //Não está sendo utilizado
                minValue: null,
                maxValue: null
            }
          }
      },
      components: {
          SgsRender,
      }
  }

</script>
<template>
  <modulePanel>
    <h3 slot="header">Página de Exemplos</h3>

    <div slot="body">
      <el-form label-position="top" label-width="100px">
        <el-row>
          <el-col :span="6">Header</el-col>
          <el-col :span="18">
            <SgsRender type="header" :attr="header"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Text</el-col>
          <el-col :span="18">
            <SgsRender type="text" :attr="text"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Numeric</el-col>
          <el-col :span="18">
            <SgsRender type="numeric" :attr="numeric"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Date</el-col>
          <el-col :span="18">
            <SgsRender type="date" :attr="date"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Date and Time</el-col>
          <el-col :span="18">
            <SgsRender type="dateTime" :attr="dateTime"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Time</el-col>
          <el-col :span="18">
            <SgsRender type="time" :attr="time"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Select</el-col>
          <el-col :span="18">
            <SgsRender type="select"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Multi Select</el-col>
          <el-col :span="18">
            <SgsRender type="multiSelect"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Text Area</el-col>
          <el-col :span="18">
            <SgsRender type="textArea" :attr="textArea"/>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="6">Upload</el-col>
          <el-col :span="18">
            <SgsRender type="upload"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">Password</el-col>
          <el-col :span="18">
            <SgsRender type="password" :attr="password"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">Button</el-col>
          <el-col :span="18">
            <SgsRender type="button"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">CPF</el-col>
          <el-col :span="18">
            <SgsRender type="cpf" :attr="cpf"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">CNPJ</el-col>
          <el-col :span="18">
            <SgsRender type="cnpj" :attr="cnpj"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">E-mail</el-col>
          <el-col :span="18">
            <SgsRender type="email" :attr="email"/>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">Currency</el-col>
          <el-col :span="18">
            <SgsRender type="currency" :attr="currency"/>
          </el-col>
        </el-row>
      </el-form>
    </div>

  </modulePanel>
</template>

