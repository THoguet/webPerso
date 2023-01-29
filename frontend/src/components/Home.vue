<script setup lang="ts">
import { defineComponent } from 'vue';
import axios, { AxiosResponse } from "axios"</script>
<script lang="ts">

let imageUrl = "image.jpg";
export default defineComponent({
  data() {
    return {
      spaceImg: ""
    }
  },
  mounted() {
    axios.get(imageUrl, { responseType: "blob" })
      .then((response: AxiosResponse) => {
        const reader = new window.FileReader();
        reader.readAsDataURL(response.data);
        reader.onload = () => {
          const imageDataUrl = (reader.result as string);
          this.spaceImg = imageDataUrl;
        }
      })
  }
});
</script>
<template>
  <div>
    <img class="child_center" style="width: 90%;height: auto;" src="<%= BASE_URL %>image/space.jpg" />
    <div style="padding:55px;" class="child_left">
      <h1 style="font-weight: inherit;">Accueil</h1>
      <p>Vous êtes à l'accueil de mon site, pensez à faire un tour sur le forum !</p>
    </div>
  </div>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
