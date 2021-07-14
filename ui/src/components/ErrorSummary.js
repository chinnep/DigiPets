const ErrorSummary = ({ errors }) => {
    return (<>{errors && errors.length > 0 && <div className="lists">
        <ul className="nes-list is-disc">
            {errors.map(e => 
            <li key={e}>{e}</li>)}
        </ul>
    </div>}</>
    );
};

export default ErrorSummary;