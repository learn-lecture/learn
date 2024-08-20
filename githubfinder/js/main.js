import Controller from "./controller/Controller.js";
import SearchUserView from "./views/SearchUserView.js";
import User from "./model/User.js";

document.addEventListener("DOMContentLoaded", main);

function main() {
    const user = new User();

    const views = {
        searchUserView: new SearchUserView(),
    };

    new Controller(user, views);
}
