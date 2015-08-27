package net.waryss.mtools.bluepurse.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import net.waryss.mtools.bluepurse.business.service.IInfoService;
import net.waryss.mtools.bluepurse.dto.InfoDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.builder.ResponseBuilder;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;

@RestController
@Api(value="info", description="retrieve some information about api")
@RequestMapping(value = "/info")
public class InfoController {

	@Autowired
	IInfoService infoService;

	public IInfoService getInfoService() {
		return infoService;
	}

	@ApiOperation(value = "api health check")
	@RequestMapping(value = "/ping", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<InfoDto> ping() throws BluePurseException {
		return new ResponseBuilder<InfoDto>().buildSucces(infoService.ping().getValue(),  null);
	}

	public void setInfoService(IInfoService infoService) {
		this.infoService = infoService;
	}

	@ApiOperation(value = "api version")
	@RequestMapping(value = "/version", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<String> version() {
		// TODO Make it better
		return new ResponseBuilder<String>().buildSucces("TODO ... but, now, it seems to be a snapshot ", null);
	}

}
