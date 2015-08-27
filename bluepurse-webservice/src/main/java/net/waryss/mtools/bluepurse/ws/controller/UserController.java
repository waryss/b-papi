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

import net.waryss.mtools.bluepurse.business.service.IUserService;
import net.waryss.mtools.bluepurse.dto.PurseDto;
import net.waryss.mtools.bluepurse.dto.ResourceDto;
import net.waryss.mtools.bluepurse.dto.SpendingDto;
import net.waryss.mtools.bluepurse.dto.UserDto;
import net.waryss.mtools.bluepurse.exception.BluePurseException;
import net.waryss.mtools.bluepurse.ws.builder.ResponseBuilder;
import net.waryss.mtools.bluepurse.ws.util.ResponseWrapper;

@RestController
@Api(value = "users", description = "user webservices")
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	IUserService userService;

	@ApiOperation(value = "create user")
	@RequestMapping(value = "", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<UserDto> createUser(@RequestBody UserDto userIn) throws BluePurseException {
		UserDto userOut = userService.createUser(userIn);
		return new ResponseBuilder<UserDto>().buildSucces("User created", userOut);
	}

	@ApiOperation(value = "create user purse")
	@RequestMapping(value = "/{userId}/purse", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> createUserpurse(@ApiParam(value = "user id") @PathVariable Long userId,
			@RequestBody PurseDto purse) throws BluePurseException {
		PurseDto purseOut = userService.createUserPurse(userId, purse);
		return new ResponseBuilder<PurseDto>().buildSucces("Created purse for user #" + userId, purseOut);
	}

	@ApiOperation(value = "create user resource")
	@RequestMapping(value = "/{userId}/resource", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> createUserresource(@ApiParam(value = "user id") @PathVariable Long userId,
			@RequestBody ResourceDto resource) throws BluePurseException {
		ResourceDto resourceOut = userService.createUserResource(userId, resource);
		return new ResponseBuilder<ResourceDto>().buildSucces("Created resource for user #" + userId, resourceOut);
	}

	@ApiOperation(value = "create user spending")
	@RequestMapping(value = "/{userId}/spending", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> createUserspending(@ApiParam(value = "user id") @PathVariable Long userId,
			SpendingDto spending) throws BluePurseException {
		SpendingDto spendingOut = userService.createUserSpending(userId, spending);
		return new ResponseBuilder<SpendingDto>().buildSucces("Created spending for user #" + userId, spendingOut);
	}

	@ApiOperation(value = "remove user")
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<UserDto> deleteUser(@ApiParam(value = "user id") @PathVariable Long userId) throws BluePurseException {
		userService.removeUser(userId);
		return new ResponseBuilder<UserDto>().buildSucces("User removed", null);
	}

	@ApiOperation(value = "delete user purse")
	@RequestMapping(value = "/{userId}/purse/{purseId}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> deleteUserpurse(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "purse id") @PathVariable Long purseId)
					throws BluePurseException {
		userService.removeUserPurse(userId, purseId);
		return new ResponseBuilder<PurseDto>().buildSucces("deleted purse with id #" + purseId + " for user #" + userId, null);
	}

	@ApiOperation(value = "delete user resource")
	@RequestMapping(value = "/{userId}/resource/{resourceId}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> deleteUserresource(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "resource id") @PathVariable Long resourceId)
					throws BluePurseException {
		userService.removeUserResource(userId, resourceId);
		return new ResponseBuilder<ResourceDto>().buildSucces("deleted resource with id #" + resourceId + " for user #" + userId, null);
	}

	@ApiOperation(value = "delete user spending")
	@RequestMapping(value = "/{userId}/spending/{spendingId}", method = RequestMethod.DELETE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> deleteUserspending(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "spending id") @PathVariable Long spendingId) throws BluePurseException {
		userService.removeUserSpending(userId, spendingId);
		return new ResponseBuilder<SpendingDto>().buildSucces("deleted spending with id #" + spendingId + " for user #" + userId, null);
	}

	@ApiOperation(value = "get user")
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<UserDto> getUser(@ApiParam(value = "user id") @PathVariable Long userId)
			throws BluePurseException {
		UserDto userOut = userService.findUser(userId);
		return new ResponseBuilder<UserDto>().buildSucces(null, userOut);
	}

	@ApiOperation(value = "get user purse")
	@RequestMapping(value = "/{userId}/purse/{purseId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> getUserpurse(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "purse id") @PathVariable Long purseId) throws BluePurseException {
		PurseDto purse = userService.findUserPurse(userId, purseId);
		return new ResponseBuilder<PurseDto>().buildSucces("purse created by User #" + userId, purse);
	}

	@ApiOperation(value = "get user purses")
	@RequestMapping(value = "/{userId}/purse", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<List<PurseDto>> getUserpurses(@ApiParam(value = "user id") @PathVariable Long userId)
			throws BluePurseException {
		List<PurseDto> purseList = userService.findUserPurses(userId);
		return new ResponseBuilder<List<PurseDto>>().buildSucces("purse list created by User #" + userId, purseList);
	}

	@ApiOperation(value = "get user resource")
	@RequestMapping(value = "/{userId}/resource/{resourceId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> getUserresource(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "resource id") @PathVariable Long resourceId) throws BluePurseException {
		ResourceDto resource = userService.findUserResource(userId, resourceId);
		return new ResponseBuilder<ResourceDto>().buildSucces("resource for User #" + userId, resource);
	}

	@ApiOperation(value = "get user resources")
	@RequestMapping(value = "/{userId}/resource", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<List<ResourceDto>> getUserresources(@ApiParam(value = "user id") @PathVariable Long userId)
			throws BluePurseException {
		List<ResourceDto> resourceList = userService.findUserResources(userId);
		return new ResponseBuilder<List<ResourceDto>>().buildSucces("resources list for User #" + userId, resourceList);
	}

	@ApiOperation(value = "get user spending")
	@RequestMapping(value = "/{userId}/spending/{spendingId}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> getUserspending(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "spending id") @PathVariable Long spendingId) throws BluePurseException {
		SpendingDto spending = userService.findUserSpending(userId, spendingId);
		return new ResponseBuilder<SpendingDto>().buildSucces("spending for User #" + userId, spending);
	}

	@ApiOperation(value = "get user spendings")
	@RequestMapping(value = "/{userId}/spending", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<List<SpendingDto>> getUserspendings(@ApiParam(value = "user id") @PathVariable Long userId)
			throws BluePurseException {
		List<SpendingDto> spendingList = userService.findUserSpendings(userId);
		return new ResponseBuilder<List<SpendingDto>>().buildSucces("spending list for User #" + userId, spendingList);
	}

	@ApiOperation(value = "partial update user")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<UserDto> partialUpadteUser(@ApiParam(value = "user id") @PathVariable Long userId,
			@RequestBody UserDto userIn) throws BluePurseException {
		UserDto userOut = userService.partialUpdateUser(userId, userIn);
		return new ResponseBuilder<UserDto>().buildSucces("User updated", userOut);
	}

	@ApiOperation(value = "partial update user purse")
	@RequestMapping(value = "/{userId}/purse/{purseId}", method = RequestMethod.PATCH, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> partialUpdateUserpurse(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "purse id") @PathVariable Long purseId, @RequestBody PurseDto purse)
					throws BluePurseException {
		PurseDto purseOut = userService.partialUpdateUserPurse(userId, purseId, purse);
		return new ResponseBuilder<PurseDto>().buildSucces("partial updated purse for user #" + userId, purseOut);
	}

	@ApiOperation(value = "partial update user resource")
	@RequestMapping(value = "/{userId}/resource/{resourceId}", method = RequestMethod.PATCH, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> partialUpdateUserresource(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "resource id") @PathVariable Long resourceId, @RequestBody ResourceDto resource)
					throws BluePurseException {
		ResourceDto resourceOut = userService.partialUpdateUserResource(userId, resourceId, resource);
		return new ResponseBuilder<ResourceDto>().buildSucces("partial updated resource for user #" + userId, resourceOut);
	}

	@ApiOperation(value = "partial update user spending")
	@RequestMapping(value = "/{userId}/spending/{spendingId}", method = RequestMethod.PATCH, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> partialUpdateUserspending(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "spending id") @PathVariable Long spendingId, SpendingDto spending) throws BluePurseException {
		SpendingDto spendingOut = userService.partialUpdateUserSpending(userId, spendingId, spending);
		return new ResponseBuilder<SpendingDto>().buildSucces("partial updated spending for user #" + userId, spendingOut);
	}

	@ApiOperation(value = "update user")
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<UserDto> updateUser(@ApiParam(value = "user id") @PathVariable Long userId,
			@RequestBody UserDto userIn) throws BluePurseException {
		UserDto userOut = userService.updateUser(userId, userIn);
		return new ResponseBuilder<UserDto>().buildSucces("User updated", userOut);
	}

	@ApiOperation(value = "update user purse")
	@RequestMapping(value = "/{userId}/purse/{purseId}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<PurseDto> updateUserpurse(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "purse id") @PathVariable Long purseId, @RequestBody PurseDto purse)
					throws BluePurseException {
		PurseDto purseOut = userService.updateUserPurse(userId, purseId, purse);
		return new ResponseBuilder<PurseDto>().buildSucces("Updated purse for user #" + userId, purseOut);
	}

	@ApiOperation(value = "update user resource")
	@RequestMapping(value = "/{userId}/resource/{resourceId}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<ResourceDto> updateUserresource(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "resource id") @PathVariable Long resourceId, @RequestBody ResourceDto resource)
					throws BluePurseException {
		ResourceDto resourceOut = userService.updateUserResource(userId, resourceId, resource);
		return new ResponseBuilder<ResourceDto>().buildSucces("Updated resource for user #" + userId, resourceOut);
	}

	@ApiOperation(value = "update user spending")
	@RequestMapping(value = "/{userId}/spending/{spendingId}", method = RequestMethod.PUT, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ResponseWrapper<SpendingDto> updateUserspending(@ApiParam(value = "user id") @PathVariable Long userId,
			@ApiParam(value = "user id") @PathVariable Long spendingId, SpendingDto spending) throws BluePurseException {
		SpendingDto spendingOut = userService.updateUserSpending(userId, spendingId, spending);
		return new ResponseBuilder<SpendingDto>().buildSucces("Updated spending for user #" + userId, spendingOut);
	}
}