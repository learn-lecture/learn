import Controller from "./controller/Controller.js";
import SearchUserView from "./views/SearchUserView.js";
import Github from "./model/Github.js";
import UserProfileView from "./views/UserProfileView.js";

document.addEventListener("DOMContentLoaded", main);

function main() {
    const github = new Github();

    const views = {
        searchUserView: new SearchUserView(),
        userProfileView: new UserProfileView()
    };

    new Controller(github, views);
}
