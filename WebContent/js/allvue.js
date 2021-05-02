
	var startKnapp = Vue.component('sesson-change-button',
	{ data: function(){
		return {  }
	},
	created(){
		console.log("button created");
	},
	props: { tekst: { type: String, default: 'Logg Inn ->'}, klasse: { type: String, default: 'vue-temp-hide' }},
	template: '<button :class=klasse>{{ tekst }}</button>'});
	
	var logintop = new Vue({
	
		el: "#top-vue-login-element",
		created(){
			console.log("created changed");
		},
		components: { startKnapp },
		methods: {
		},

	
	});
	
	
	
