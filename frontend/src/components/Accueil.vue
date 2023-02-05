<script setup lang="ts">
import { defineComponent } from 'vue';
import axios, { AxiosResponse } from "axios"</script>
<script lang="ts">

export default defineComponent({
});
</script>
<template>
  <div>
    <img style="width: 90%;height: auto;" src="/images/space.jpg" />
    <div id="content">
      <h1 style="font-weight: inherit;">Accueil</h1>
      <p>Vous êtes à l'accueil de mon site, pensez à faire un tour sur le forum !</p>
    </div>
  </div>
</template>

<style scoped>
#content {
  text-align: left;
  padding: 55px;
}
</style>
