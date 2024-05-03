import {Navigate, Outlet} from "react-router-dom";

type ProtectedRouteProps = {
    userName:string | undefined
}
export default function ProtectedRoute(props:ProtectedRouteProps){

    const user = props.userName !== undefined && props.userName !== "anonymousUser"

    return(
        user ? <Outlet /> : <Navigate to={"/"} />
    )

}