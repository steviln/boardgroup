 
	var playerschema = new Vue({
		el: "#player-edit-form",
		data: function(){
			return {
				data: {	
					id: document.getElementById('objectid').value,			
					fornavn: document.getElementById('fornavn').value,
					etternavn: document.getElementById('etternavn').value,
					brukernavn: document.getElementById('brukernavn').value,
					passorden: document.getElementById('passorden').value,
					passordto: document.getElementById('passordto').value,
					epost: document.getElementById('epost').value
				}			
			}
		},
		methods: {

			doSendPlayer: function(){
				console.log("skjema skal sende " + this.data);
				var denne = this;
	
				axios.post('/BoardgamesRegister/ajax/saveplayer',this.data)
					.then(function(response){
						console.log(response);
						if(response.data.retur == 0){
							//denne.errormessage = "feil brukernavn og/eller passord";
						}else if(response.data.retur > 0){
							//location.reload();
						}
					})
				.catch(function(error){
				});
				
			}
		}
	
	
	});