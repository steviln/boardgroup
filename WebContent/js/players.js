 
	var playerschema = new Vue({
		el: "#player-edit-form",
		data: function(){
			return {
				fornavn: document.getElementById('fornavn').value,
				etternavn: document.getElementById('etternavn').value,
				brukernavn: document.getElementById('brukernavn').value,
				passorden: document.getElementById('passorden').value,
				passordto: document.getElementById('passordto').value,
				epost: document.getElementById('epost').value			
			}
		},
		methods: {

			doSendPlayer: function(){
				alert("skjema skal sende " + this.fornavn);
			}
		}
	
	
	});