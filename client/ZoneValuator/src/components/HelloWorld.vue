<template>
  <div class="container">
    <h1 class="green">Zone Valuator</h1>

    <div>
      <label>Longitude:</label>
      <input type="text" class="form-control" id="longitude" v-model="longitude">
    </div>

    <div>
      <label>Latitude:</label>
      <input type="text" class="form-control" id="latitude" v-model="latitude">
    </div>

    <div>
      <label>Rayon:</label>
      <input type="text" class="form-control" id="rayon" v-model="rayon">
    </div>

    <div>
      <button class="btn btn-primary" @click="telechargerLignesVente">Télécharger les lignes de vente</button>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      longitude: '',
      latitude: '',
      rayon: '',
    };
  },
  methods: {
    telechargerLignesVente() {
      alert("test")
    },
  },
};
</script>

<style>
/* Styles pour aligner verticalement les champs */
.container {
  width: 80%;
}

.label {
  width: 50px;
  text-align: left;
}

.form-group {
  margin-bottom: 15px;
}

/* Styles pour le bouton */
.btn {
  padding: 10px 20px;
  font-size: 16px;
}
</style>