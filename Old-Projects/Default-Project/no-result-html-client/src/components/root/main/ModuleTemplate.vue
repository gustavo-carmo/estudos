<template>
  <main>

    <div class="container-fluid" v-show="title">
      <div :class="isFluid()">
        <div class="page-header">
          <h2>{{ title }}</h2>
        </div>
      </div>
    </div>

    <!--<div style="height: 50px; background-color: #ccc" v-show="subtitle">
      <h2>
        {{ subtitle }}
      </h2>
    </div>-->

    <section :class="isFluid()">
      <slot></slot>
    </section>

  </main>
</template>
<script>
    export default{
      props: ['title', 'subtitle', 'fluid'],
      data() {
        return {}
      },
      components: {
      },
      methods: {
        isFluid: function() {
          if (this.fluid == "true") {
            return "container-fluid"
          }
          return "container"
        }
      }
    }
</script>
