<template>
  <div class="container">
    <div class="form">
      <h1 class="green">Zone Valuator</h1>

      <div class="form__group field">
        <input type="input" class="form__field" placeholder="Longitude" name="longitude" id='longitude' required />
        <label for="longitude" class="form__label">Longitude</label>
      </div>

      <div class="form__group field">
        <input type="input" class="form__field" placeholder="Latitude" name="latitude" id='latitude' required />
        <label for="latitude" class="form__label">Latitude</label>
      </div>

      <div class="form__group field">
        <input type="input" class="form__field" placeholder="Rayon" name="rayon" id='rayon' required />
        <label for="rayon" class="form__label">Rayon</label>
      </div>

      <div>
        <button class="btn rounded-button" @click="telechargerLignesVente">Télécharger les lignes de vente</button>
      </div>
      <button @click="$emit('explode')">Show confetti</button>
    </div>
  </div>
</template>

<script setup>

</script>
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
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}


.form__group {
  position: relative;
  padding: 15px 0 0;
  margin-top: 5px;
  margin-bottom: 5px;
  width: 100%;
}

.form__field {
  font-family: inherit;
  width: 100%;
  border: 0;
  border-bottom: 2px solid #9b9b9b;
  outline: 0;
  font-size: 1.3rem;
  color: #fff;
  padding: 7px 0;
  background: transparent;
  transition: border-color 0.2s;
}
.form__field::placeholder {
  color: transparent;
}
.form__field:placeholder-shown ~ .form__label {
  font-size: 1.3rem;
  cursor: text;
  top: 20px;
}

.form__label {
  position: absolute;
  top: 0;
  display: block;
  transition: 0.2s;
  font-size: 1rem;
  color: #9b9b9b;
}

.form__field:focus {
  padding-bottom: 6px;
  font-weight: 700;
  border-width: 3px;
  border-image: linear-gradient(to right, #11998e, #38ef7d);
  border-image-slice: 1;
}
.form__field:focus ~ .form__label {
  position: absolute;
  top: 0;
  display: block;
  transition: 0.2s;
  font-size: 1rem;
  color: #11998e;
  font-weight: 700;
}

/* reset input */
.form__field:required, .form__field:invalid {
  box-shadow: none;
}

.rounded-button {
  width: 100%;
  margin-top: 10px;
  padding: 5px;
  border-radius: 5px;
  border: solid;
  border-color: hsla(160, 100%, 37%, 1);
  background-color: transparent;
  color: black;
  font-size: 16px;
  transition: background-color 0.3s ease;
  font-weight: bold;
  color: white;
  cursor: pointer;
}

/* Style pour le hover */
.rounded-button:hover {
  background-color: hsla(160, 100%, 37%, 1);
  color: white;
}
</style>