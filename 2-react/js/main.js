class App extends React.Component {

    constructor() {
        super();

        this.state = {
            searchKeyword: "",
        };
    }

    handleChangeInput(event) {
        // this.state.searchKeyword = event.target.value;
        // this.forceUpdate();
        const searchKeyword = event.target.value;
        this.setState({ searchKeyword });
    }

    handleSubmit(event) {
        event.preventDefault();
        console.log("handle submit: ", this.state.searchKeyword);
    }

    render() {
        // let resetButton =null;

        // if (this.state.searchKeyword.length > 0) {
        //     resetButton = <button type="reset" className="btn-reset"></button>;
        // }

        return (
            <>
                <header>
                    <h2 className="container">검색</h2>
                </header>
                <div className="container">
                    <form onSubmit={event => this.handleSubmit(event)}>
                        <input
                            type="text"
                            placeholder="검색어를 입력하세요."
                            value={this.state.searchKeyword}
                            onChange={event => this.handleChangeInput(event)}
                            autoFocus
                        />
                        {this.state.searchKeyword.length > 0 && (
                            <button type="reset" className="btn-reset"></button>
                        )}
                    </form>
                </div>
            </>
        );
    }

}

ReactDOM.render(<App />, document.querySelector("#app"));