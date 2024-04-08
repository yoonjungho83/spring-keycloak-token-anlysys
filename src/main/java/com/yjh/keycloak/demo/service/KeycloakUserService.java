package com.yjh.keycloak.demo.service;

//@RequiredArgsConstructor
//@Service
//@Slf4j
public class KeycloakUserService {

//    @Value("${keycloak.realm}")
//    private String realm;
//    private final Keycloak keycloakBean;

//    public KeycloakUserService(Keycloak keycloak) {
//        this.keycloak = keycloak;
//    }

//    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {
//
//        UserRepresentation user=new UserRepresentation();
//        user.setEnabled(true);
//        user.setUsername(userRegistrationRecord.username());
//        user.setEmail(userRegistrationRecord.email());
//        user.setFirstName(userRegistrationRecord.firstName());
//        user.setLastName(userRegistrationRecord.lastName());
//        user.setEmailVerified(false);
//
//        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
//        credentialRepresentation.setValue(userRegistrationRecord.password());
//        credentialRepresentation.setTemporary(false);
//        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//
//        List<CredentialRepresentation> list = new ArrayList<>();
//        list.add(credentialRepresentation);
//        user.setCredentials(list);
//
//        UsersResource usersResource = getUsersResource();
//
//        Response response = usersResource.create(user);
//
//        if(Objects.equals(201,response.getStatus())){
//
//            List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
//            if(!CollectionUtils.isEmpty(representationList)){
//                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
//                assert userRepresentation1 != null;
//                emailVerification(userRepresentation1.getId());
//            }
//            return  userRegistrationRecord;
//        }
//
////        response.readEntity()
//
//        return null;
//    }
//
//    private UsersResource getUsersResource() {
////        RealmResource realm1 = keycloakBean.realm(realm);
////        return realm1.users();
//    	return null;
//    }
//
//    public UserRepresentation getUserById(String userId) {
//
//
//        return  getUsersResource().get(userId).toRepresentation();
//    }
//
//    public void deleteUserById(String userId) {
//
//        getUsersResource().delete(userId);
//    }
//
//
//    public void emailVerification(String userId){
//
//        UsersResource usersResource = getUsersResource();
//        usersResource.get(userId).sendVerifyEmail();
//    }
//
//    public UserResource getUserResource(String userId){
//        UsersResource usersResource = getUsersResource();
//        return usersResource.get(userId);
//    }
//
//    public void updatePassword(String userId) {
//
//        UserResource userResource = getUserResource(userId);
//        List<String> actions= new ArrayList<>();
//        actions.add("UPDATE_PASSWORD");
//        userResource.executeActionsEmail(actions);
//
//    }
}
