<?php
namespace Hbase;

/**
 * Autogenerated by Thrift Compiler (0.9.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
use Thrift\Base\TBase;
use Thrift\Type\TType;
use Thrift\Type\TMessageType;
use Thrift\Exception\TException;
use Thrift\Exception\TProtocolException;
use Thrift\Protocol\TProtocol;
use Thrift\Exception\TApplicationException;


class TCell {
  static $_TSPEC;

  public $value = null;
  public $timestamp = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'value',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'timestamp',
          'type' => TType::I64,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['value'])) {
        $this->value = $vals['value'];
      }
      if (isset($vals['timestamp'])) {
        $this->timestamp = $vals['timestamp'];
      }
    }
  }

  public function getName() {
    return 'TCell';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->value);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->timestamp);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('TCell');
    if ($this->value !== null) {
      $xfer += $output->writeFieldBegin('value', TType::STRING, 1);
      $xfer += $output->writeString($this->value);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->timestamp !== null) {
      $xfer += $output->writeFieldBegin('timestamp', TType::I64, 2);
      $xfer += $output->writeI64($this->timestamp);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class ColumnDescriptor {
  static $_TSPEC;

  public $name = null;
  public $maxVersions = 3;
  public $compression = "NONE";
  public $inMemory = false;
  public $bloomFilterType = "NONE";
  public $bloomFilterVectorSize = 0;
  public $bloomFilterNbHashes = 0;
  public $blockCacheEnabled = false;
  public $timeToLive = -1;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'name',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'maxVersions',
          'type' => TType::I32,
          ),
        3 => array(
          'var' => 'compression',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'inMemory',
          'type' => TType::BOOL,
          ),
        5 => array(
          'var' => 'bloomFilterType',
          'type' => TType::STRING,
          ),
        6 => array(
          'var' => 'bloomFilterVectorSize',
          'type' => TType::I32,
          ),
        7 => array(
          'var' => 'bloomFilterNbHashes',
          'type' => TType::I32,
          ),
        8 => array(
          'var' => 'blockCacheEnabled',
          'type' => TType::BOOL,
          ),
        9 => array(
          'var' => 'timeToLive',
          'type' => TType::I32,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['name'])) {
        $this->name = $vals['name'];
      }
      if (isset($vals['maxVersions'])) {
        $this->maxVersions = $vals['maxVersions'];
      }
      if (isset($vals['compression'])) {
        $this->compression = $vals['compression'];
      }
      if (isset($vals['inMemory'])) {
        $this->inMemory = $vals['inMemory'];
      }
      if (isset($vals['bloomFilterType'])) {
        $this->bloomFilterType = $vals['bloomFilterType'];
      }
      if (isset($vals['bloomFilterVectorSize'])) {
        $this->bloomFilterVectorSize = $vals['bloomFilterVectorSize'];
      }
      if (isset($vals['bloomFilterNbHashes'])) {
        $this->bloomFilterNbHashes = $vals['bloomFilterNbHashes'];
      }
      if (isset($vals['blockCacheEnabled'])) {
        $this->blockCacheEnabled = $vals['blockCacheEnabled'];
      }
      if (isset($vals['timeToLive'])) {
        $this->timeToLive = $vals['timeToLive'];
      }
    }
  }

  public function getName() {
    return 'ColumnDescriptor';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->name);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->maxVersions);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->compression);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->inMemory);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->bloomFilterType);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->bloomFilterVectorSize);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->bloomFilterNbHashes);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->blockCacheEnabled);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 9:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->timeToLive);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('ColumnDescriptor');
    if ($this->name !== null) {
      $xfer += $output->writeFieldBegin('name', TType::STRING, 1);
      $xfer += $output->writeString($this->name);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->maxVersions !== null) {
      $xfer += $output->writeFieldBegin('maxVersions', TType::I32, 2);
      $xfer += $output->writeI32($this->maxVersions);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->compression !== null) {
      $xfer += $output->writeFieldBegin('compression', TType::STRING, 3);
      $xfer += $output->writeString($this->compression);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->inMemory !== null) {
      $xfer += $output->writeFieldBegin('inMemory', TType::BOOL, 4);
      $xfer += $output->writeBool($this->inMemory);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->bloomFilterType !== null) {
      $xfer += $output->writeFieldBegin('bloomFilterType', TType::STRING, 5);
      $xfer += $output->writeString($this->bloomFilterType);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->bloomFilterVectorSize !== null) {
      $xfer += $output->writeFieldBegin('bloomFilterVectorSize', TType::I32, 6);
      $xfer += $output->writeI32($this->bloomFilterVectorSize);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->bloomFilterNbHashes !== null) {
      $xfer += $output->writeFieldBegin('bloomFilterNbHashes', TType::I32, 7);
      $xfer += $output->writeI32($this->bloomFilterNbHashes);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->blockCacheEnabled !== null) {
      $xfer += $output->writeFieldBegin('blockCacheEnabled', TType::BOOL, 8);
      $xfer += $output->writeBool($this->blockCacheEnabled);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->timeToLive !== null) {
      $xfer += $output->writeFieldBegin('timeToLive', TType::I32, 9);
      $xfer += $output->writeI32($this->timeToLive);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class TRegionInfo {
  static $_TSPEC;

  public $startKey = null;
  public $endKey = null;
  public $id = null;
  public $name = null;
  public $version = null;
  public $serverName = null;
  public $port = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'startKey',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'endKey',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'id',
          'type' => TType::I64,
          ),
        4 => array(
          'var' => 'name',
          'type' => TType::STRING,
          ),
        5 => array(
          'var' => 'version',
          'type' => TType::BYTE,
          ),
        6 => array(
          'var' => 'serverName',
          'type' => TType::STRING,
          ),
        7 => array(
          'var' => 'port',
          'type' => TType::I32,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['startKey'])) {
        $this->startKey = $vals['startKey'];
      }
      if (isset($vals['endKey'])) {
        $this->endKey = $vals['endKey'];
      }
      if (isset($vals['id'])) {
        $this->id = $vals['id'];
      }
      if (isset($vals['name'])) {
        $this->name = $vals['name'];
      }
      if (isset($vals['version'])) {
        $this->version = $vals['version'];
      }
      if (isset($vals['serverName'])) {
        $this->serverName = $vals['serverName'];
      }
      if (isset($vals['port'])) {
        $this->port = $vals['port'];
      }
    }
  }

  public function getName() {
    return 'TRegionInfo';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->startKey);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->endKey);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->id);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->name);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::BYTE) {
            $xfer += $input->readByte($this->version);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->serverName);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->port);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('TRegionInfo');
    if ($this->startKey !== null) {
      $xfer += $output->writeFieldBegin('startKey', TType::STRING, 1);
      $xfer += $output->writeString($this->startKey);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->endKey !== null) {
      $xfer += $output->writeFieldBegin('endKey', TType::STRING, 2);
      $xfer += $output->writeString($this->endKey);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->id !== null) {
      $xfer += $output->writeFieldBegin('id', TType::I64, 3);
      $xfer += $output->writeI64($this->id);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->name !== null) {
      $xfer += $output->writeFieldBegin('name', TType::STRING, 4);
      $xfer += $output->writeString($this->name);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->version !== null) {
      $xfer += $output->writeFieldBegin('version', TType::BYTE, 5);
      $xfer += $output->writeByte($this->version);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->serverName !== null) {
      $xfer += $output->writeFieldBegin('serverName', TType::STRING, 6);
      $xfer += $output->writeString($this->serverName);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->port !== null) {
      $xfer += $output->writeFieldBegin('port', TType::I32, 7);
      $xfer += $output->writeI32($this->port);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class Mutation {
  static $_TSPEC;

  public $isDelete = false;
  public $column = null;
  public $value = null;
  public $writeToWAL = true;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'isDelete',
          'type' => TType::BOOL,
          ),
        2 => array(
          'var' => 'column',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'value',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'writeToWAL',
          'type' => TType::BOOL,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['isDelete'])) {
        $this->isDelete = $vals['isDelete'];
      }
      if (isset($vals['column'])) {
        $this->column = $vals['column'];
      }
      if (isset($vals['value'])) {
        $this->value = $vals['value'];
      }
      if (isset($vals['writeToWAL'])) {
        $this->writeToWAL = $vals['writeToWAL'];
      }
    }
  }

  public function getName() {
    return 'Mutation';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->isDelete);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->column);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->value);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::BOOL) {
            $xfer += $input->readBool($this->writeToWAL);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('Mutation');
    if ($this->isDelete !== null) {
      $xfer += $output->writeFieldBegin('isDelete', TType::BOOL, 1);
      $xfer += $output->writeBool($this->isDelete);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->column !== null) {
      $xfer += $output->writeFieldBegin('column', TType::STRING, 2);
      $xfer += $output->writeString($this->column);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->value !== null) {
      $xfer += $output->writeFieldBegin('value', TType::STRING, 3);
      $xfer += $output->writeString($this->value);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->writeToWAL !== null) {
      $xfer += $output->writeFieldBegin('writeToWAL', TType::BOOL, 4);
      $xfer += $output->writeBool($this->writeToWAL);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class BatchMutation {
  static $_TSPEC;

  public $row = null;
  public $mutations = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'row',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'mutations',
          'type' => TType::LST,
          'etype' => TType::STRUCT,
          'elem' => array(
            'type' => TType::STRUCT,
            'class' => '\Hbase\Mutation',
            ),
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['row'])) {
        $this->row = $vals['row'];
      }
      if (isset($vals['mutations'])) {
        $this->mutations = $vals['mutations'];
      }
    }
  }

  public function getName() {
    return 'BatchMutation';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->row);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::LST) {
            $this->mutations = array();
            $_size0 = 0;
            $_etype3 = 0;
            $xfer += $input->readListBegin($_etype3, $_size0);
            for ($_i4 = 0; $_i4 < $_size0; ++$_i4)
            {
              $elem5 = null;
              $elem5 = new \Hbase\Mutation();
              $xfer += $elem5->read($input);
              $this->mutations []= $elem5;
            }
            $xfer += $input->readListEnd();
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('BatchMutation');
    if ($this->row !== null) {
      $xfer += $output->writeFieldBegin('row', TType::STRING, 1);
      $xfer += $output->writeString($this->row);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->mutations !== null) {
      if (!is_array($this->mutations)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('mutations', TType::LST, 2);
      {
        $output->writeListBegin(TType::STRUCT, count($this->mutations));
        {
          foreach ($this->mutations as $iter6)
          {
            $xfer += $iter6->write($output);
          }
        }
        $output->writeListEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class TIncrement {
  static $_TSPEC;

  public $table = null;
  public $row = null;
  public $column = null;
  public $ammount = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'table',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'row',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'column',
          'type' => TType::STRING,
          ),
        4 => array(
          'var' => 'ammount',
          'type' => TType::I64,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['table'])) {
        $this->table = $vals['table'];
      }
      if (isset($vals['row'])) {
        $this->row = $vals['row'];
      }
      if (isset($vals['column'])) {
        $this->column = $vals['column'];
      }
      if (isset($vals['ammount'])) {
        $this->ammount = $vals['ammount'];
      }
    }
  }

  public function getName() {
    return 'TIncrement';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->table);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->row);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->column);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->ammount);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('TIncrement');
    if ($this->table !== null) {
      $xfer += $output->writeFieldBegin('table', TType::STRING, 1);
      $xfer += $output->writeString($this->table);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->row !== null) {
      $xfer += $output->writeFieldBegin('row', TType::STRING, 2);
      $xfer += $output->writeString($this->row);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->column !== null) {
      $xfer += $output->writeFieldBegin('column', TType::STRING, 3);
      $xfer += $output->writeString($this->column);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ammount !== null) {
      $xfer += $output->writeFieldBegin('ammount', TType::I64, 4);
      $xfer += $output->writeI64($this->ammount);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class TRowResult {
  static $_TSPEC;

  public $row = null;
  public $columns = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'row',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'columns',
          'type' => TType::MAP,
          'ktype' => TType::STRING,
          'vtype' => TType::STRUCT,
          'key' => array(
            'type' => TType::STRING,
          ),
          'val' => array(
            'type' => TType::STRUCT,
            'class' => '\Hbase\TCell',
            ),
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['row'])) {
        $this->row = $vals['row'];
      }
      if (isset($vals['columns'])) {
        $this->columns = $vals['columns'];
      }
    }
  }

  public function getName() {
    return 'TRowResult';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->row);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::MAP) {
            $this->columns = array();
            $_size7 = 0;
            $_ktype8 = 0;
            $_vtype9 = 0;
            $xfer += $input->readMapBegin($_ktype8, $_vtype9, $_size7);
            for ($_i11 = 0; $_i11 < $_size7; ++$_i11)
            {
              $key12 = '';
              $val13 = new \Hbase\TCell();
              $xfer += $input->readString($key12);
              $val13 = new \Hbase\TCell();
              $xfer += $val13->read($input);
              $this->columns[$key12] = $val13;
            }
            $xfer += $input->readMapEnd();
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('TRowResult');
    if ($this->row !== null) {
      $xfer += $output->writeFieldBegin('row', TType::STRING, 1);
      $xfer += $output->writeString($this->row);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->columns !== null) {
      if (!is_array($this->columns)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('columns', TType::MAP, 2);
      {
        $output->writeMapBegin(TType::STRING, TType::STRUCT, count($this->columns));
        {
          foreach ($this->columns as $kiter14 => $viter15)
          {
            $xfer += $output->writeString($kiter14);
            $xfer += $viter15->write($output);
          }
        }
        $output->writeMapEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class TScan {
  static $_TSPEC;

  public $startRow = null;
  public $stopRow = null;
  public $timestamp = null;
  public $columns = null;
  public $caching = null;
  public $filterString = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'startRow',
          'type' => TType::STRING,
          ),
        2 => array(
          'var' => 'stopRow',
          'type' => TType::STRING,
          ),
        3 => array(
          'var' => 'timestamp',
          'type' => TType::I64,
          ),
        4 => array(
          'var' => 'columns',
          'type' => TType::LST,
          'etype' => TType::STRING,
          'elem' => array(
            'type' => TType::STRING,
            ),
          ),
        5 => array(
          'var' => 'caching',
          'type' => TType::I32,
          ),
        6 => array(
          'var' => 'filterString',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['startRow'])) {
        $this->startRow = $vals['startRow'];
      }
      if (isset($vals['stopRow'])) {
        $this->stopRow = $vals['stopRow'];
      }
      if (isset($vals['timestamp'])) {
        $this->timestamp = $vals['timestamp'];
      }
      if (isset($vals['columns'])) {
        $this->columns = $vals['columns'];
      }
      if (isset($vals['caching'])) {
        $this->caching = $vals['caching'];
      }
      if (isset($vals['filterString'])) {
        $this->filterString = $vals['filterString'];
      }
    }
  }

  public function getName() {
    return 'TScan';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->startRow);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->stopRow);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->timestamp);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::LST) {
            $this->columns = array();
            $_size16 = 0;
            $_etype19 = 0;
            $xfer += $input->readListBegin($_etype19, $_size16);
            for ($_i20 = 0; $_i20 < $_size16; ++$_i20)
            {
              $elem21 = null;
              $xfer += $input->readString($elem21);
              $this->columns []= $elem21;
            }
            $xfer += $input->readListEnd();
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->caching);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->filterString);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('TScan');
    if ($this->startRow !== null) {
      $xfer += $output->writeFieldBegin('startRow', TType::STRING, 1);
      $xfer += $output->writeString($this->startRow);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->stopRow !== null) {
      $xfer += $output->writeFieldBegin('stopRow', TType::STRING, 2);
      $xfer += $output->writeString($this->stopRow);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->timestamp !== null) {
      $xfer += $output->writeFieldBegin('timestamp', TType::I64, 3);
      $xfer += $output->writeI64($this->timestamp);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->columns !== null) {
      if (!is_array($this->columns)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('columns', TType::LST, 4);
      {
        $output->writeListBegin(TType::STRING, count($this->columns));
        {
          foreach ($this->columns as $iter22)
          {
            $xfer += $output->writeString($iter22);
          }
        }
        $output->writeListEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    if ($this->caching !== null) {
      $xfer += $output->writeFieldBegin('caching', TType::I32, 5);
      $xfer += $output->writeI32($this->caching);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->filterString !== null) {
      $xfer += $output->writeFieldBegin('filterString', TType::STRING, 6);
      $xfer += $output->writeString($this->filterString);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class IOError extends TException {
  static $_TSPEC;

  public $message = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'message',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['message'])) {
        $this->message = $vals['message'];
      }
    }
  }

  public function getName() {
    return 'IOError';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->message);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('IOError');
    if ($this->message !== null) {
      $xfer += $output->writeFieldBegin('message', TType::STRING, 1);
      $xfer += $output->writeString($this->message);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class IllegalArgument extends TException {
  static $_TSPEC;

  public $message = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'message',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['message'])) {
        $this->message = $vals['message'];
      }
    }
  }

  public function getName() {
    return 'IllegalArgument';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->message);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('IllegalArgument');
    if ($this->message !== null) {
      $xfer += $output->writeFieldBegin('message', TType::STRING, 1);
      $xfer += $output->writeString($this->message);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class AlreadyExists extends TException {
  static $_TSPEC;

  public $message = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'message',
          'type' => TType::STRING,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['message'])) {
        $this->message = $vals['message'];
      }
    }
  }

  public function getName() {
    return 'AlreadyExists';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->message);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('AlreadyExists');
    if ($this->message !== null) {
      $xfer += $output->writeFieldBegin('message', TType::STRING, 1);
      $xfer += $output->writeString($this->message);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}


