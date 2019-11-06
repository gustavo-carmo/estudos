<script>
import { mapActions } from 'vuex';
import appConfig from '../../../services/AppConfigService';

export default {
  data() {
    return {
      instance: {
        id: '',
        type: '',
        instance: 'contract',
        successMessage: ''
      },
      imageUrl: ''
    }
  },
  computed: {
    isValidToSubmit: function() {
      return this.instance.imageUrl != '';
    },
  },
  mounted() {
    this.instance.id = this.$route.params.id;
    this.instance.type = this.$route.params.type;
  },
  methods: {
    ...mapActions([
      'showLoader',
      'hideLoader',
      'contractDoSearch'
    ]),
    serverUrl: function() {
      return appConfig.apiUrl() + '/api/upload';
    },
    onChange: function(file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {

      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size < 950000;

      if (!isJPG) {
        this.$message.error('Imagem precisa ser JPG/PNG format!');
      }
      if (!isLt2M) {
        this.$message.error('Imagem não pode ser maior que 2MB!');
      }
      return isJPG && isLt2M;
    },
    submitUpload() {
      this.showLoader();
      this.$refs.upload.submit();
    },
    handleAvatarSuccess(res, file) {
      this.contractDoSearch();
      this.hideLoader();
      this.$notify({
        title: 'Sucesso',
        message: (this.instance.type == 'logo' ? 'Logo' : 'Background') + ' atualizado com sucesso.',
        type: 'success'
      });
      this.$router.push('/contract/show/' + this.instance.id);
    },
    backToDetails: function() {
      this.$router.push('/contract/show/' + this.instance.id);
    }
  }
}
</script>

<template>
  <modulePanel>
    <div slot="body">

      <el-form label-position="top">
        <el-row :gutter="10">

          <el-col :span="12">
            <el-upload
              :data="instance"
              :auto-upload="false"
              ref="upload"
              class="avatar-uploader"
              :action="serverUrl()"
              :show-file-list="false"
              :on-change="onChange"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-col>

        </el-row>
      </el-form>
    </div>

    <el-row :gutter="10" slot="footer">
      <el-col :span="24">
        <el-button size="small" @click.prevent="backToDetails()">
          <i class="glyphicon glyphicon-chevron-left" />
          Voltar para detalhes
        </el-button>
        <el-button type="primary" size="small" @click.prevent="submitUpload()" :disabled="!imageUrl">
          <i class="glyphicon glyphicon-ok"/> Enviar
        </el-button>
      </el-col>
    </el-row>

  </modulePanel>
</template>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

input.el-upload__input {
  display: none;
}
</style>
