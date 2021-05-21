

	const eventbus = new Vue();
	
	Vue.component('login-skjema',
	{  data: function(){
		return {
			skjema: {
				brukernavn: "",
				passord: ""
			},
			errormessage: ""
		}
	},
	methods: {
		doLogin: function(){
		
			const formData = new URLSearchParams();
			formData.append('brukernavn',this.skjema.brukernavn);
			formData.append('passord',this.skjema.passord);
				//console.log(formData);		
			axios.post('/BoardgamesRegister/ajax/login',formData)
			//axios.post('http://127.0.0.1/apitest.php',formData)
			.then(function(response){
					if(response.data.retur == 0){
						console.log("er jer inne");
						this.errormessage = "feil brukernavn og/eller passord";
					}else if(response.data.retur > 0){
						location.reload();
					}
				})
			.catch(function(error){
			});
		}
	},
	template: '<div><input type="text" name="brukernavn" v-model="skjema.brukernavn" placeholder="Brukernavn" /><input type="password" name="passord" v-model="skjema.passord" placeholder="Passord" /><button type="button" v-on:click="doLogin">Logg inn</button><div class="error-message">{{errormessage}}</div></div>'

	});
	
	Vue.component('sesson-change-button',
	{ data: function(){
		return { 
			tekst: "",
			klasse: "vue-temp-hide",
			buttonmode: 0
		 }
	},
	methods: {
		visLogin: function(){
			
			if(this.buttonmode == 1){
				var modalElement = document.getElementById('loginModal');
				var loginModal = new bootstrap.Modal(modalElement,{ backdrop: true });
				loginModal.show();
			}else if(this.buttonmode == 2){
				axios.get('/BoardgamesRegister/ajax/logout').then(response => {
					location.reload();
				});
			}
		}
	},
	created(){
		//console.log("button created");
		var denne = this;
		eventbus.$on('sessionloadcheck', function(value){ 
			if(value.loggid == 0){
				denne.tekst = "Logg inn";
				denne.klasse = "vue-temp-show";
				denne.buttonmode = 1;
				console.log("er ikke logget paa");
			}else{
				denne.tekst = "Logg ut";
				denne.klasse = "vue-temp-show";
				denne.buttonmode = 2;
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
	
	
	
