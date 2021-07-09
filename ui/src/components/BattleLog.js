const BattleLog = ({ log }) => {
    return (<>{log && log.length > 0 && <div className="lists">
        <ul className="nes-list is-disc">
            {log.map(e => <li key={e}>{e}</li>)}
        </ul>
    </div>}</>
    );
};

export default BattleLog;