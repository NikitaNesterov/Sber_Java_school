public class CustomerService {

//        private Logger logger = Logger.getLogger(LoginService.class);
//    private final UserRepository userRepo;
//
//@Autowired
//    public LoginService(UserRepository userRepo) {
//        this.userRepo = userRepo;
//    }
//
//    public boolean authenticate(LoginForm loginForm) {
//        logger.info("try auth with user-form: " + loginForm);
//                return userRepo.findUser(loginForm.getUsername(), loginForm.getPassword()) ||
//                        loginForm.getUsername().equals("root") && loginForm.getPassword().equals("123");
//    }
//
//    public boolean ifUserValid(LoginForm loginForm) {
//         return loginForm.getUsername().equals("") && loginForm.getPassword().equals("");
//    }
//
//    public boolean userRegistrate(LoginForm loginForm) {
//        if (!ifUserValid(loginForm) && !checkUser(loginForm)) {
//            logger.info("new username and password ARE VALID: " + loginForm);
//            return true;
//        } else {
//            logger.error("new username and password ARE NOT VALID: " + loginForm);
//            return false;
//        }
//    }
//
//
//    public List<User> getAllUsers() { return userRepo.retreiveAllUsers();
//    }
//
//    public boolean checkUser(LoginForm loginForm) {
//        for (User users : getAllUsers()) {
//            if(users.getUsername().equals(loginForm.getUsername())) {
//                logger.info("user is already present: " + users.getUsername());
//                return true;
//            }
//        }
//          {
//            logger.info("user is not found: " + loginForm.getUsername());
//            return false;
//        }
//    }
//
//    public void saveUser(LoginForm loginForm) {
//      userRepo.store(loginForm);
//    }

}
