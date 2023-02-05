<template>
	<div class="corps">
		<header>
			<h1 class="titre">Site de Nessar</h1>
		</header>
		<div class="menu">
			<ul style="padding-left: 0px;margin-bottom:0px;">
				<li v-for="route in routes">
					<router-link :to="route.path">{{ route.title }}</router-link>
				</li>
			</ul>
		</div>
		<router-view></router-view>
	</div>
</template>

<script setup lang="ts">
import { defineComponent } from 'vue';
import { routes } from './main';
</script>
<script lang="ts">
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@700&family=Yrsa&display=swap');

* {
	padding: 0;
	box-sizing: border-box;
}

a {
	text-decoration: none;
	color: white;
}

a:hover {
	color: #a8a6a1;
	transition: color .2s;
}

header {
	display: flex;
	flex-direction: column;
}

.corps {
	display: flex;
	flex-direction: column;
	position: sticky;
	text-align: center;
	margin: auto;
	background-color: white;
	max-height: 90%;
}

.titre {
	font-family: 'Yrsa', serif;
	font-weight: 100;
	padding-top: 30px;
	margin: 0px;
	color: #333;
	padding-bottom: 30px;
	font-size: 4em;
}

li {
	list-style: none;
	display: inline-block;
	padding-left: 0.4em;
	padding-top: 0.8em;
	padding-bottom: 0.8em;
	margin-right: 1em;
}

ul {
	margin: 0px;
}

.menu {
	display: flex;
	flex-direction: column;
	letter-spacing: 1px;
	font-weight: 700;
	font-size: 20px;
	font-family: 'Source Sans Pro', sans-serif;
	background-color: #333;
	color: white;
}
</style>
