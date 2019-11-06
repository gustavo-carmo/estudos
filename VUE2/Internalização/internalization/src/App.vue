<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <hello></hello>

    <br>
    <el-button @click.prevent="setEn()">English</el-button>
    <el-button @click.prevent="setPtBr()">Português(Br)</el-button>
    <el-button @click.prevent="setEs()">Epanhõl</el-button>
    <h1>
    {{ $t("message")}}
    </h1>
    <h1>
    {{ $t("test") }}
    </h1>
    <h1>
    {{ $t("el.datepicker.now")}}
    </h1>
    <hr>
    <hr>
    key
    <el-input v-model="key"/>
    pt
    <el-input v-model="pt"/>
    en
    <el-input v-model="en"/>
    es
    <el-input v-model="es"/>
    <hr>
    {{$t("localeTest")}}
    {{$t("abobrinha")}}
    {{$t("telegrama")}}
    {{$t(bla)}}

    <pre>{{$i18n.messages.pt.appKeys}}</pre>

    <br>

    <el-button @click.prevent="addLocale()">ADD LOCALE</el-button>
    <hr>
    {{variable}}
    <el-button @click.prevent="changeVariable()">CHANGE VARIABLE</el-button>

    <hr>

    <GmapAutocomplete @place_changed="setPlace">
    </GmapAutocomplete>
    <button @click="usePlace">Add</button>

    <gmap-map
      :center="center"
      :zoom="10"
      map-type-id="terrain"
      style="width: 1000px; height: 500px" >
      <gmap-marker
        v-if="place"
        :position="{
          lat: place.geometry.location.lat(),
          lng: place.geometry.location.lng(),
        }"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>

<script>
import hello from './components/Hello'
import messages from './i18n';

export default {
  name: 'app',
  data () {
    return {
      pt: '',
      en: '',
      es: '',
      key: '',
      bla: 'ble',
      variable: 'AAAAAAA',
      place: null,
      center: {
        lat: -21,
        lng: -41
      },
      markers: []
    }
  },
  components: {
    hello
  },
  methods: {
    addLocale: function() {
      //window.alert("ABCDE!!!");
      console.log(this.$i18n.messages);
      this.$i18n.messages.pt['appKeys'][this.key] = this.pt;
      this.$i18n.messages.pt[this.key] = this.pt;
      this.$i18n.messages.en[this.key] = this.en;
      this.$i18n.messages.es[this.key] = this.es;
      // this.$i18n.messages = messages;
      this.$i18n.locale = this.$i18n.locale;
    },
    usePlace: function() {
      this.markers.push(this.place);
    },
    setPlace(place) {
      this.place = place;
    },
    changeVariable: function() {
      this.changeVariable2(this.variable);
    },
    changeVariable2: function(variable) {
      variable = 'Abobrinha -- MUAHAHAH';
    },
    setPtBr: function() {
      this.$i18n.locale = 'pt';
    },
    setEn: function() {
      this.$i18n.locale = 'en';
    },
    setEs: function() {
      this.$i18n.locale = 'es';
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
