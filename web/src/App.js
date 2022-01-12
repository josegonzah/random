import {Provider} from "react-redux";
import generateStore from "./redux/store";
import Home from "./containers/Home";

function App() {
    const store = generateStore();
    return (
        <Provider store={store}>
            <Home/>
        </Provider>
    );
}

export default App;
