package net.waryss.mtools.bluepurse.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import net.waryss.mtools.bluepurse.business.service.IPurseService;
import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.builder.ResponseBuilder;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;

@RestController
@Api(value = "purse", description = "purse webservices")
@RequestMapping(value = "/purse")
public class PurseController {

	@Autowired
	IPurseService purseService;

	@ApiOperation(value = "create purse")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> createpurse(@RequestBody PurseDto purseIn) throws BluePurseException {
		PurseDto purseOut = purseService.createPurse(purseIn);
		return new ResponseBuilder<PurseDto>().buildSucces("purse created", purseOut);
	}

	@ApiOperation(value = "delete purse")
	@RequestMapping(value = "/{purseId}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> delatetepurse(@ApiParam(value="purse id") @PathVariable Long purseId) throws BluePurseException {
		purseService.removePurse(purseId);
		return new ResponseBuilder<PurseDto>().buildSucces("purse deleted", null);
	}

	@ApiOperation(value = "get purse")
	@RequestMapping(value = "/{purseId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> getpurse(@ApiParam(value = "purse id") @PathVariable Long purseId)
			throws BluePurseException {
		PurseDto purseOut = purseService.findPurse(purseId);
		return new ResponseBuilder<PurseDto>().buildSucces(null, purseOut);
	}

	@ApiOperation(value = "partial update purse")
	@RequestMapping(value = "/{purseId}", method = RequestMethod.PATCH, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> partialUpadtepurse(@ApiParam(value="purse id") @PathVariable Long purseId, @RequestBody PurseDto purseIn) throws BluePurseException {
		PurseDto purseout = purseService.partialUpdatePurse(purseId, purseIn);
		return new ResponseBuilder<PurseDto>().buildSucces("purse updated", purseout);
	}

	@ApiOperation(value = "update purse")
	@RequestMapping(value = "/{purseId}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> updatepurse(@ApiParam(value="purse id") @PathVariable Long purseId, @RequestBody PurseDto purseIn) throws BluePurseException {
		PurseDto purseOut = purseService.updatePurse(purseId, purseIn);
		return new ResponseBuilder<PurseDto>().buildSucces("purse updated", purseOut);
	}
}
