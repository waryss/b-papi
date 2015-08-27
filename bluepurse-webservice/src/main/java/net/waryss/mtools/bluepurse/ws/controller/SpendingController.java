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

import net.waryss.mtools.bluepurse.business.service.ISpendingService;
import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.builder.ResponseBuilder;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;

@RestController
@Api(value = "spending", description = "spending webservices")
@RequestMapping(value = "/spending")
public class SpendingController {

	@Autowired
	ISpendingService spendingService;

	@ApiOperation(value = "create spending")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> createspending(@RequestBody SpendingDto spendingIn) throws BluePurseException {
		SpendingDto spendingOut = spendingService.createSpending(spendingIn);
		return new ResponseBuilder<SpendingDto>().buildSucces("spending created", spendingOut);
	}

	@ApiOperation(value = "delete spending")
	@RequestMapping(value = "/{spendingId}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> deletespending(@ApiParam(value = "spending id") @PathVariable Long spendingId)
			throws BluePurseException {
		spendingService.removeSpending(spendingId);
		return new ResponseBuilder<SpendingDto>().buildSucces("spending deleted", null);
	}

	@ApiOperation(value = "get spending")
	@RequestMapping(value = "/{spendingId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> getspending(@ApiParam(value = "spending id") @PathVariable Long spendingId)
			throws BluePurseException {
		SpendingDto spendingOut = spendingService.findSpending(spendingId);
		return new ResponseBuilder<SpendingDto>().buildSucces(null, spendingOut);
	}

	@ApiOperation(value = "partial update spending")
	@RequestMapping(value = "/{spendingId}", method = RequestMethod.PATCH, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> partialUpadtespending(@ApiParam(value = "spending id") @PathVariable Long spendingId,
			@RequestBody SpendingDto spendingIn) throws BluePurseException {
		SpendingDto spendingout = spendingService.partialUpdateSpending(spendingId, spendingIn);
		return new ResponseBuilder<SpendingDto>().buildSucces("spending updated", spendingout);
	}

	@ApiOperation(value = "update spending")
	@RequestMapping(value = "/{spendingId}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> updatespending(@ApiParam(value = "spending id") @PathVariable Long spendingId,
			@RequestBody SpendingDto spendingIn) throws BluePurseException {
		SpendingDto spendingOut = spendingService.updateSpending(spendingId, spendingIn);
		return new ResponseBuilder<SpendingDto>().buildSucces("spending updated", spendingOut);
	}
}
