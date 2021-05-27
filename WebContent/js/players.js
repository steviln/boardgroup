 
	var playerschema = new Vue({
		el: "#player-edit-form",
		data: function(){
			return {
				fornavn: "",
				etternavn: "",
				brukernavn: "",
				passorden: "",
				passordto: "",
				epost: ""			}
		},
		methods: {

			doSendPlayer: function(){
				alert("skjema skal sende " + this.fornavn);
			}
		}
	
	
	});