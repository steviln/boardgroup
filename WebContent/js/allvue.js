

	const eventbus = new Vue();
	
	Vue.component('login-skjema',
	{  data: function(){
		return {
			skjema: {
				brukernavn: "",
				passord: ""
			}
		}
	},
	methods: {
		doLogin: function(){
			console.log("data " + this.skjema.brukernavn + ":" + this.skjema.passord);
			axios.post('/BoardgamesRegister/ajax/login',{ brukernavn: this.skjema.brukernavn,passord: this.skjema.passord })
			.then(function(response){
				console.log(response);
				})
			.catch<(function(error){
			});
		}
	},
	template: '<div><input type="text" name="brukernavn" v-model="skjema.brukernavn" placeholder="Brukernavn" /><input type="password" name="passord" v-model="skjema.passord" placeholder="Passord" /><button type="button" v-on:click="doLogin">Logg inn</button></div>'

	});
	
	Vue.component('sesson-change-button',
	{ data: function(){
		return { 
			tekst: "",
			klasse: "vue-temp-hide"
		 }
	},
	methods: {
		visLogin: function(){
			var modalElement = document.getElementById('loginModal');
			//console.log(modalElement);
			var loginModal = new bootstrap.Modal(modalElement,{ backdrop: true });
			//console.log(loginModal);
			loginModal.show();
		}
	},
	created(){
		//console.log("button created");
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
	template: '<button @click="visLogin" class="login-button" :class=klasse>{{ tekst }}</button>'});
	
	var logintop = new Vue({
	
		el: "#top-vue-login-element",
		created(){
			//console.log("created change, send with bus");
			
		},
		mounted(){
			//console.log("main mounted");
			axios.get('/BoardgamesRegister/ajax/getsession').then(response => {
				eventbus.$emit('sessionloadcheck', response.data);
			});
			
		},
		methods: {
		},	
	});
	
	var loginfelt = new Vue({
		el: "#vue-login-skjema-holder"
	});
	
	
	
