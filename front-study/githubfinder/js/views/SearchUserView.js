import View from "./View.js";
import {on, qs} from "../global/helpers.js";

export default class SearchUserView extends View {

    constructor() {
        super(qs('#search-section'));
        this.inputElement = qs("[type=text]", this.element);

        this.bindEvents();
    }

    bindEvents() {
        on(this.inputElement, "keypress", (event) => this.handleKeyup(event));
    }

    handleKeyup(event) {
        if (event.keyCode !== 13) return;

        const nickname = this.inputElement.value;
        this.emit("search", { nickname });
    }

}