

	const eventbus = new Vue();
	
	Vue.component('sesson-change-button',
	{ data: function(){
		return { 
			tekst: "",
			klasse: "vue-temp-hide"
		 }
	},
	created(){
		console.log("button created");
		var denne = this;
		eventbus.$on('sessionloadcheck', function(value){ 
			if(value.loggid == 0){
				denne.tekst = "Logg inn";
				denne.klasse = "vue-temp-show";
				console.log("er ikke logget paa");
			}else{
				denne.tekst = "Logg ut";
				denne.klasse = "vue-temp-show";
				console.log("er logget på");	
			} 
			});
	},
	template: '<button :class=klasse>{{ tekst }}</button>'});
	
	var logintop = new Vue({
	
		el: "#top-vue-login-element",
		created(){
			console.log("created change, send with bus");
			
		},
		mounted(){
			console.log("main mounted");
			axios.get('/BoardgamesRegister/ajax/getsession').then(response => {
				eventbus.$emit('sessionloadcheck', response.data);
			});
			
		},
		methods: {
		},

	
	});
	
	
	
