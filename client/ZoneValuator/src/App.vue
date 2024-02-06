<script setup>
import HelloWorld from './components/HelloWorld.vue'
import ConfettiExplosion from "vue-confetti-explosion";
import { nextTick, ref } from "vue";

const visible = ref(false);

const explode = async () => {
  visible.value = false;
  await nextTick();
  visible.value = true;
};
</script>

<template>
  <div style="width: 100%; height: 100vh; overflow-y: hidden; overflow-x: hidden;">

    <ConfettiExplosion v-if="visible" :particleCount="5000" :particleSize="5" :stageWidth="2000" :force="1" :duration="10000"
    :colors="['#00bd7e', '#06F98E', '#15FF00', '#00FFBA']"
     style="margin-left:50%; width: 100%;"/>
    <HelloWorld @explode="explode"/>
  </div>
</template>

<style scoped>
</style>
