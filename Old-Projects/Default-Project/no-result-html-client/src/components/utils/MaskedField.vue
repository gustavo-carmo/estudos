<script>
    export default{
        props: ["mask", "placeholder", "value", "error", "disabled", "type"],
        data(){
            return{
                valueToSend: '',
                typeToDisplay: 'input'
            }
        },
        watch: {
          value: function(val) {
            this.valueToSend = val;
          }
        },
        mounted () {
          let typeToAssign = this.type;

          if (typeToAssign) {
            this.typeToDisplay = typeToAssign;
          }
        },
        computed: {
          displayInput: function () {
            if (this.typeToDisplay === 'input') {
              return true;
            }
            return false;
          },
          displaySpan: function () {
            if (this.typeToDisplay === 'span') {
              return true;
            }
            return false;
          }
        },
        methods: {
          updateValue: function () {
            this.$emit('input', this.valueToSend);
          }
        }
    }

</script>

<template>
  <div>
    <div class="el-form-item__content">
      <div class="el-input" v-show="displayInput"
           :class="{ 'has-error' : error, 'is-disabled' : disabled }">
        <input class="form-control el-input__inner"
               v-mask="mask"
               v-model="valueToSend"
               :id="idValue"
               :placeholder="placeholder"
               :disabled="disabled"
               @input="updateValue()"
        />
      </div>
    </div>
    <span v-show="displaySpan">
      {{valueToSend}}
    </span>
  </div>
</template>
