class BoxInfoPresenter{
	debugger
	constructor(apiPath, contextPath)
	{
		this.apiPath =  apiPath;
		this.worker = null;
		this.contextPath = contextPath;
		this.data = null;
	}
	
	doFetch() {
		
		if (this.worker == null )
		{
			this.worker = new Worker(this.contextPath + '/assets/appJs/CommonWorker.js');
			this.worker.addEventListener('message', (e) => this.processData(e));
		}
		
		const url = this.contextPath + this.apiPath + "boxInfo";
		window.loadCounter++;
		this.worker.postMessage({
			"method" : "GET" ,
			"url" : url 
		});
	}
	
	processData(e){
	debugger
		if (e.data!= "" || e.data!= null )
		{
			this.data = e.data;
			console.log(this.data);	
			$('#PENDING_ADA').text((this.getValueFor("PENDING_ADA")));
			$('#PENDING_ASA').text((this.getValueFor("PENDING_ASA")));
			$('#FUND_DTM').text((this.getValueFor("FUND_DTM")));
			$('#FUND_AFM').text((this.getValueFor("FUND_AFM")));
			
			$('#FUND_RFM').text((this.getValueFor("FUND_RFM")));
			$('#FUND_DTA').text((this.getValueFor("FUND_DTA")));
			$('#TOTAL_BB').text((this.getValueFor("TOTAL_BB")));
			$('#TOTAL_SA').text((this.getValueFor("TOTAL_SA")));
			
			$('#TOTAL_DA').text((this.getValueFor("TOTAL_DA")));
			$('#TOTAL_COMP').text((this.getValueFor("TOTAL_COMP")));
			$('#TOTAL_ACT').text((this.getValueFor("TOTAL_ACT")));
			$('#TOTAL_SUB_ACT').text((this.getValueFor("TOTAL_SUB_ACT")));
		}
		
		window.loadCounter--;
	}
	
	getValueFor(key){
		let retVal = 0;
		var result = Object.entries(this.data);
		result.filter( el => {
			if( el[0] == key)
			{
				retVal =el[1];
				
			}
		});
		
		return retVal;
	}
}