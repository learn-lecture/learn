import Controller from "./controller/Controller.js";
import SearchUserView from "./views/SearchUserView.js";
import Github from "./model/Github.js";
import UserProfileView from "./views/UserProfileView.js";
import UserReposView from "./views/UserReposView.js";

document.addEventListener("DOMContentLoaded", main);

function main() {
    const github = new Github();

    const views = {
        searchUserView: new SearchUserView(),
        userProfileView: new UserProfileView(),
        userReposView: new UserReposView()
    };

    new Controller(github, views);
}
