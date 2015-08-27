package net.waryss.mtools.bluepurse.ws.controller;

import java.util.List;

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

import net.waryss.mtools.bluepurse.business.service.IResourceService;
import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.builder.ResponseBuilder;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;

@RestController
@Api(value = "resources", description = "resource webservices")
@RequestMapping(value = "/resource")
public class ResourceController {

	@Autowired
	IResourceService resourceService;

	@ApiOperation(value = "create resource")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> createresource(@RequestBody ResourceDto resourceIn) throws BluePurseException {
		ResourceDto resourceOut = resourceService.createResource(resourceIn);
		return new ResponseBuilder<ResourceDto>().buildSucces("resource created", resourceOut);
	}

	@ApiOperation(value = "delete resource")
	@RequestMapping(value = "", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> delateteresource(@ApiParam(value="resource id") @PathVariable Long resourceId) throws BluePurseException {
		resourceService.removeResource(resourceId);
		return new ResponseBuilder<ResourceDto>().buildSucces("resource deleted", null);
	}

	@ApiOperation(value = "get resource")
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> getresource(@ApiParam(value="resource id") @PathVariable Long resourceId) throws BluePurseException {
		ResourceDto resourceOut = resourceService.findResource(resourceId);
		return new ResponseBuilder<ResourceDto>().buildSucces(null, resourceOut);
	}

	@ApiOperation(value = "list all resources")
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<List<ResourceDto>> getresources() throws BluePurseException {
		List<ResourceDto> resourceOutList = resourceService.getAllResource();
		return new ResponseBuilder<List<ResourceDto>>().buildSucces(null, resourceOutList);
	}

	@ApiOperation(value = "partial update resource")
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.PATCH, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> partialUpadteresource(@ApiParam(value="resource id") @PathVariable Long resourceId, @RequestBody ResourceDto resourceIn) throws BluePurseException {
		ResourceDto resourceout = resourceService.partialUpdateResource(resourceId, resourceIn);
		return new ResponseBuilder<ResourceDto>().buildSucces("resource updated", resourceout);
	}

	@ApiOperation(value = "update resource")
	@RequestMapping(value = "/{resourceId}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> updateresource(@ApiParam(value="resource id") @PathVariable Long resourceId, @RequestBody ResourceDto resourceIn) throws BluePurseException {
		ResourceDto resourceOut = resourceService.updateResource(resourceId, resourceIn);
		return new ResponseBuilder<ResourceDto>().buildSucces("resource updated", resourceOut);
	}
}
