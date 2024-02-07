<template>
  <div class="container">
    <div class="form">
      <h1 class="green">Zone Valuator</h1>

      <div class="form__group field">
        <input type="number" v-model="longitude" class="form__field" placeholder="Longitude" name="longitude" id='longitude' required />
        <label for="longitude" class="form__label">Longitude</label>
      </div>

      <div class="form__group field">
        <input type="number" v-model="latitude" class="form__field" placeholder="Latitude" name="latitude" id='latitude' required />
        <label for="latitude" class="form__label">Latitude</label>
      </div>

      <div class="form__group field">
        <input type="number"  v-model="rayon" class="form__field" placeholder="Rayon" name="rayon" id='rayon' required />
        <label for="rayon" class="form__label">Rayon</label>
      </div>

      <div>
        <button v-if="longitude && latitude && rayon" class="rounded-button" @click="telechargerLignesVente">
          <span v-if="waitResponse || sended_pdf" class="loader"></span>
          <span v-else>Télécharger les lignes de vente</span>
        </button>
        <button v-else class="btn-disable rounded-button" @click="showError">Télécharger les lignes de vente</button>
      </div>
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
      sended_pdf: false,
      id_pdf: null,
      waitResponse: false,
      last_path: false
    };
  },
  methods: {
    telechargerLignesVente() {
      this.sended_pdf = true
      const url = `http://localhost:8080/generatePdfByLocation?latitude=${this.latitude}&longitude=${this.longitude}&rayon=${this.rayon}`

      fetch(url)
          .then(response => response.json())
          .then(data => {
            this.id_pdf = data
          })
          .catch(error => {
            this.sended_pdf = false
            console.error('Une erreur s\'est produite :', error);
          });
    },
    download_path() {
      this.$emit('explode')
      let link = document.createElement('a');
      link.href = this.last_path;
      link.target = '_blank'
      link.download = `lignes_de_vente_${this.latitude}_${this.longitude}_${this.rayon}`;
      link.dispatchEvent(new MouseEvent('click'));
    },
    showError() {
      alert("Vous devez renseigner ".concat(this.longitude ? (this.latitude ? (this.rayon ? 'ERROR' : 'un rayon') : 'une latitude') : ' une longitude'))
    },
    get_response() {
      const url = `http://localhost:8080/getPdf/${this.id_pdf}`

      return new Promise((resolve, reject) => {
        fetch(url).then(response => response.json())
            .then(data => resolve(data))
            .catch(error => {
              console.error('Une erreur s\'est produite :', error);
              this.waitResponse = false
              reject(false)
            });
      })
    },
    wait_response() {
      const intervalId = setInterval(() => {
        if (!this.waitResponse) {
          clearInterval(intervalId);
        } else {
          this.get_response().then((response) => {
            if (!response) {
              this.waitResponse = false;
            } else {
              if (response.hasOwnProperty('path') && response.path.length > 0) {
                this.last_path = response.path
                this.waitResponse = false;
                this.id_pdf = null;
              }
            }
          })
        }
      }, 2000);
    }
  },
  watch: {
    id_pdf: {
      handler() {
        if (this.id_pdf) {
          this.waitResponse = true
          this.wait_response()
        } else {
          this.sended_pdf = false;
        }
      }
    },
    last_path() {
      if (this.last_path) {
        this.download_path()
      }
    }
  }
};
</script>

<style scoped>
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

.loader {
  width: 20px;
  height: 20px;
  border: 3px solid #FFF;
  border-bottom-color: transparent;
  border-radius: 50%;
  display: inline-block;
  box-sizing: border-box;
  animation: rotation 1s linear infinite;
}

@keyframes rotation {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>