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
* {
	padding: 0;
	box-sizing: border-box;
}

body {
	overflow: hidden;
	height: 100%;
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

span {
	position: absolute;
	pointer-events: none;
	background: #fff;
	animation: animate 5s linear;
	z-index: 1;
}

.corps {
	display: flex;
	flex-direction: column;
	position: sticky;
	text-align: center;
	min-width: 70%;
	max-width: 70%;
	margin: auto;
	background-color: white;
	z-index: 2;
	min-height: 100%;
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

body {
	margin-top: 0px;
	background-color: #212121;
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

.bouton {
	transition: color .0s;
}

input {
	border-radius: 5px;
}

.inputbutton {
	color: white;
	background-color: #000000;
	/* border:none; */
	border-radius: 5px;
	text-align: center;
	padding-left: 3px;
	padding-right: 3px;
}

.inputbutton:hover {
	background-color: #494949;
	font-weight: bold;
}

#bouton {
	font-family: 'Source Sans Pro', sans-serif;
	height: 24px;
	width: 200px;
	background-color: #000000;
	border: none;
	border-radius: 5px;
	text-align: center;
	margin: auto;
}

#bouton:hover {
	background-color: #494949;
	font-weight: bold;
}

.imagets {
	float: right;
	padding: 1.3em;
}

.paddingdiv {
	padding: 10%;
}

.imagegifts {
	padding-left: 184px;
	padding-right: 184px;
}

.imageM {
	display: none;
}
</style>
